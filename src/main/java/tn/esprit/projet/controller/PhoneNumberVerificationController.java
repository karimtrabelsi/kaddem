package tn.esprit.projet.controller;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;

import static com.twilio.example.ValidationExample.ACCOUNT_SID;
import static com.twilio.example.ValidationExample.AUTH_TOKEN;


@RestController
@RequestMapping(path = "api/phoneNumber")
@Slf4j
public class PhoneNumberVerificationController {

    @GetMapping(value = "/generateTOTP")
    public ResponseEntity<String> generateTOTP(){

        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Verification verification = Verification.creator(
                        "VA6b974f8b24a45b9a78c62b59eb31a3e3", // this is your verification sid
                        "+18059181374", //this is your Twilio verified recipient phone number
                        "sms") // this is your channel type
                .create();

        System.out.println(verification.getStatus());

        log.info("TOTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

        return new ResponseEntity<>("Your TOTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @GetMapping("/verifyTOTP")
    public ResponseEntity<?> verifyUserTOTP() throws Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {

            VerificationCheck verificationCheck = VerificationCheck.creator(
                            "VA6b974f8b24a45b9a78c62b59eb31a3e3")
                    .setTo("+18059181374")
                    .setCode("486578")
                    .create();

            System.out.println(verificationCheck.getStatus());

        } catch (Exception e) {
            return new ResponseEntity<>("Verification failed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("This user's verification has been completed successfully", HttpStatus.OK);
    }
}
