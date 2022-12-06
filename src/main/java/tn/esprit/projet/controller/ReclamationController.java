package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.services.IReclamationService;

import java.util.List;
import java.util.Properties;


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

    @Operation(summary = "Get AllReclamation", description = "Afficher la liste des Reclamations ")
    @GetMapping("/getReclamation")
    public List<Reclamation> GetR(){

        return  ReclamationService.getAllReclamation();
    }


    @Operation(summary = "Add Reclamation", description = "Ajouter des Reclamations ")
    @PostMapping("/addReclamation/{email}")
    public void  addReclamation(@RequestBody Reclamation R,@PathVariable("email") String email){

        ReclamationService.addReclamation(R,email);
        ReclamationService.sendMessageWithAttachment(R.getDescription(),email);

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


}
