package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.IContratService;

import java.util.List;

@RestController
@RequestMapping("Contrat")
@Tag(name ="Contrat" ,description = "Gestion de Contrats ")

public class ContratController {
    @Autowired
    IContratService contratService;

    @Operation(summary = "Get Contrat By Id", description = "Afficher des Contrat avec id ")
    @GetMapping("/getone/{idContrat}")
    public Contrat getContratbyid(@PathVariable("idContrat") Long id){

        return  contratService.getContratbyid(id);
    }

    @Operation(summary = "Get AllContrat", description = "Afficher la liste des Contrat ")
    @GetMapping("/getContrat")
    public List<Contrat> GetC(){

        return  contratService.getAllContrat();
    }

    @Operation(summary = "Add Contrat", description = "Ajouter un Contrat")
    @PostMapping("/addContrat")
    public void  addContrat(@RequestBody Contrat C){

        contratService.addContrat(C);
    }

   /* @Operation(summary = "Add List Contrat", description = "Ajouter une liste des Contrats ")
    @PostMapping("addContrats")
    public List<Contrat> addContrat(@RequestBody List<Contrat> listContrat) {
        return contratService.addContrat(listContrat);
    } */



    @Operation(summary = "Update Contrat", description = "Modifier un Contrat ")
    @PutMapping("/updateContrat/{idContrat}")
    public void updateC(@PathVariable("idContrat") Long id, @RequestBody Contrat C){

        C.setIdContrat(id);
        contratService.updateContrat(C);
    }

    @Operation(summary = "Delete a Contrat  By ID", description = "Supprimer un Contrat  par son id")
    @DeleteMapping("/delContrat/{idContrat}")
    public  void deleteC(@PathVariable("idContrat") Long id){

        contratService.deleteContrat(id);
    }
}
