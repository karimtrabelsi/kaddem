package tn.esprit.projet.controller;


import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tn.esprit.projet.entities.ERole;
import tn.esprit.projet.entities.Role;
import tn.esprit.projet.entities.User;
import tn.esprit.projet.payload.request.LoginRequest;
import tn.esprit.projet.payload.request.SignupRequest;
import tn.esprit.projet.payload.response.MessageResponse;
import tn.esprit.projet.payload.response.UserInfoResponse;
import tn.esprit.projet.repository.RoleRepository;
import tn.esprit.projet.repository.UserRepository;
import tn.esprit.projet.security.JwtUtils;
import tn.esprit.projet.services.UserDetailsImpl;
import tn.esprit.projet.services.UserService;

import static com.twilio.example.ValidationExample.ACCOUNT_SID;
import static com.twilio.example.ValidationExample.AUTH_TOKEN;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true")
@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("logged in");

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getEmail(),
                        userDetails.getPhoneNumber(),
                        roles));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Phone number is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getPhoneNumber(),
                false);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        generateTOTP(signUpRequest.getPhoneNumber());
    log.info("aa");
        return ResponseEntity.ok(new MessageResponse("User registered successfully!" +
                "We sent you an sms to verify your phone number"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        log.info("logged out");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @PostMapping("/forgotpassword")
    public String processForgotPassword(@RequestParam  String email) {
        String token = RandomString.make(45);
        try {
            userService.updateResetPasswordToken(token,email);
            String resetPasswordlink="http://localhost:8080/api/auth/resetPassword?token="+token;
            sendEmail(email, resetPasswordlink);
        } catch (UsernameNotFoundException | MessagingException | UnsupportedEncodingException ex){

        }

return "Email sent";
    }

    private void sendEmail(String email, String resetPasswordlink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("testahmedahmed210@gmail.com","Kaddem support");
        helper.setTo(email);
        String subject="Heres the link to reset your password";
        String content="<p>Hello,</p>" +
                "<p> You have requested to reset your password.</p>" +
                "<p> Click the link below to change the password:</p>"  +
                "<p><b><a href=\""+resetPasswordlink +"\">Change my password</a><b></p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }

    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@Param(value="token") String token, Model model) {

        User user = userService.get(token);
        if (user == null){
            model.addAttribute("title","Reset password");
            model.addAttribute("message","Invalid token");
            return "reset_password_form";
        }
        model.addAttribute("token",token);
        model.addAttribute("pageTitle","Reset your password");
        return "reset_password_form";
    }

    @PostMapping("/resetPassword")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.get(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset_password_form";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "reset_password_form";

    }
    @GetMapping(value = "/generateTOTP")
    public ResponseEntity<String> generateTOTP(String phone){

        Twilio.init("ACc8dd32d8ca05e1431deac8f46c8cffb2", "e77b1e678ce2076bff28bbf39b6c8cd4");

        Verification verification = Verification.creator(
                        "VA6b974f8b24a45b9a78c62b59eb31a3e3", // this is your verification sid
                        phone, //this is your Twilio verified recipient phone number
                        "sms") // this is your channel type
                .create();

        System.out.println(verification.getStatus());

        log.info("TOTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

        return new ResponseEntity<>("Your TOTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @Operation(description = "Verify Code")
    @GetMapping("/verifyTOTP")
    public ResponseEntity<?> verifyUserTOTP(@RequestParam String phone,@RequestParam String code) throws Exception {
        Twilio.init("ACc8dd32d8ca05e1431deac8f46c8cffb2", "e77b1e678ce2076bff28bbf39b6c8cd4");

        try {

            VerificationCheck verificationCheck = VerificationCheck.creator(
                            "VA6b974f8b24a45b9a78c62b59eb31a3e3")
                    .setTo(phone)
                    .setCode(code)
                    .create();

            User u=userRepository.findByPhoneNumber(phone);
            u.setEnabled(true);
            userRepository.save(u);
            System.out.println(verificationCheck.getStatus());

        } catch (Exception e) {
            return new ResponseEntity<>("Verification failed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("This user's verification has been completed successfully", HttpStatus.OK);
    }
}
