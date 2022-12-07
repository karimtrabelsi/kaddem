package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.entities.Type;
import tn.esprit.projet.repository.ReclamationRepository;
import tn.esprit.projet.services.IReclamationService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("Reclamation")
@Tag(name ="Reclamation" ,description = "Gestion de Reclamation ")
public class ReclamationController {
    @Autowired
    IReclamationService ReclamationService;
    @Autowired
    private JavaMailSender emailSender;

    @Operation(summary = "Get Reclamation By Id", description = "Afficher des Contrat avec id ")
    @GetMapping("/getone/{idReclamation}")
    public Reclamation getReclamationbyid(@PathVariable("idReclamation") Long id){

        return  ReclamationService.getReclamationbyid(id);
    }

    @Operation(summary = "Get Reclamation By Type", description = "Afficher Reclamation avec Type")
    @GetMapping("getReclamationBytype/{type}")
    public List<Reclamation> getReclamationbyType(@PathVariable Type type) {
        return ReclamationService.getReclamationByType(type);
    }

    @Operation(summary = "Get All Reclamations", description = "Afficher la liste des Reclamations ")
    @GetMapping("/getReclamation")
    public List<Reclamation> GetR(){

        return  ReclamationService.getAllReclamation();
    }


    @Operation(summary = "Add Reclamation", description = "Ajouter des Reclamations ")
    @PostMapping("/addReclamation/{email}")
    public void  addReclamation(@RequestBody Reclamation R,@PathVariable("email") String email){

        ReclamationService.addReclamation(R,email);
        System.out.println(R.getUser().getFirstName());
        ReclamationService.sendMessageWithAttachment(R.getUser().getFirstName(),R.getType(),email);

    }
   /* @EventListener(ApplicationReadyEvent.class)
    public void sendEmail(){
        ReclamationService.sendMessageWithAttachment("Text");
    }*/


    @Operation(summary = "Update Reclamation", description = "Modifier des Reclamations ")
    @PutMapping("/updateReclamation/{idReclamation}")
    public void updateReclamation(@PathVariable("idReclamation") Long id, @RequestBody Reclamation R){

        R.setIdReclamation(id);
        ReclamationService.updateReclamation(R);
    }

    @Operation(summary = "Delete Reclamation", description = "Supprimer des Reclamations ")
    @DeleteMapping("/delReclamation/{idReclamation}")
    public  void deleteR(@PathVariable("idReclamation") Long id){

        ReclamationService.deleteReclamation(id);
    }

    @Operation(summary = "Confirm Reclamation", description = "confirmReclamation")
    @PutMapping("confirmReclamation/{idReclamation}")
    public void confirmReclamation(@PathVariable long idReclamation) {

        ReclamationService.confirmReclamation(idReclamation);
    }

    @Operation(summary = "Upload Image", description = "uploadimage")
    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("screenshot") MultipartFile file) throws IOException {
        File convertFile = new File("C:\\Users\\MSI\\Desktop\\img\\path"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
    }

    @Operation(summary = "Add Reclamation with screenshot", description = "Ajouter des Reclamations ")
    @RequestMapping(value = "/addReclamationWithScreen/{email}" , method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public @ResponseBody void  addReclamationImg(@RequestPart Reclamation R,@PathVariable("email") String email,@RequestPart("screenshot") MultipartFile file)throws IOException {

        File convertFile = new File("C:\\Users\\MSI\\Desktop\\img\\path"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        R.setScreenshot(convertFile.getPath());
        ReclamationService.addReclamation(R,email);

    }


}
