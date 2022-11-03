package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.services.IReclamationService;

import java.util.List;

@RestController
@RequestMapping("Reclamation")
@Tag(name ="Reclamation" ,description = "Gestion de Reclamation ")
public class ReclamationController {
    @Autowired
    IReclamationService ReclamationService;

    @Operation(summary = "Get Contrat By Id", description = "Afficher des Contrat avec id ")
    @GetMapping("/getone/{idReclamation}")
    public Reclamation getReclamationbyid(@PathVariable("idReclamation") Long id){

        return  ReclamationService.getReclamationbyid(id);
    }

    @Operation(summary = "Get AllReclamation", description = "Afficher la liste des Reclamations ")
    @GetMapping("/getReclamation")
    public List<Reclamation> GetR(){

        return  ReclamationService.getAllReclamation();
    }

    @Operation(summary = "Add AllReclamation", description = "Ajouter des Reclamations ")
    @PostMapping("/addReclamation")
    public void  addReclamation(@RequestBody Reclamation R){

        ReclamationService.addReclamation(R);
    }


    @Operation(summary = "Update AllReclamation", description = "Modifier des Reclamations ")
    @PutMapping("/updateReclamation/{idReclamation}")
    public void updateReclamation(@PathVariable("idReclamation") Long id, @RequestBody Reclamation R){

        R.setIdReclamation(id);
        ReclamationService.updateReclamation(R);
    }

    @Operation(summary = "Delete AllReclamation", description = "Supprimer des Reclamations ")
    @DeleteMapping("/delReclamation/{idReclamation}")
    public  void deleteR(@PathVariable("idReclamation") Long id){

        ReclamationService.deleteReclamation(id);
    }


}
