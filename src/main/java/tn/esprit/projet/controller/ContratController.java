package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Specialite;
import tn.esprit.projet.services.IContratService;

import java.util.Date;
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

    @Operation(summary = "Get All Contracts", description = "Afficher la liste des Contrat ")
    @GetMapping("/getContrat")
    public List<Contrat> GetC(){

        return  contratService.getAllContrat();
    }

    @Operation(summary = "Get Archived Contrats", description = "Afficher la liste des Contrat Archivees ")
    @GetMapping("/getArContrat")
    public List<Contrat> getAllContratArchive(){

        return  contratService.getAllContratArchive();
    }

    @Operation(summary = "Get All Contrat By Speciality", description = "Afficher la liste des Contrat d'une meme specialite ")
    @GetMapping("getContratBySpecialite/{specialite}")
    public List<Contrat> getContratBySpecialite(@PathVariable Specialite specialite) {
        return contratService.getContratBySpecialite(specialite);
    }

    @Operation(summary = "Add Contract", description = "Ajouter un Contrat")
    @PostMapping("/addContrat")
    public void  addContrat(@RequestBody Contrat C){

        contratService.addContrat(C);
    }

   /* @Operation(summary = "Add List Contrat", description = "Ajouter une liste des Contrats ")
    @PostMapping("addContrats")
    public List<Contrat> addContrat(@RequestBody List<Contrat> listContrat) {
        return contratService.addContrat(listContrat);
    } */



    @Operation(summary = "Update Contract", description = "Modifier un Contrat ")
    @PutMapping("/updateContrat/{idContrat}")
    public void updateC(@PathVariable("idContrat") Long id, @RequestBody Contrat C){

        C.setIdContrat(id);
        contratService.updateContrat(C);
    }

    @Operation(summary = "Delete a Contract By ID", description = "Supprimer un Contrat  par son id")
    @DeleteMapping("/delContrat/{idContrat}")
    public  void deleteC(@PathVariable("idContrat") Long id){

        contratService.deleteContrat(id);
    }

    @Operation(summary = "Affect Contract to user", description = "affectContratToEtudiant")
    @PutMapping(value = "/affectContratToEtudiant/{nom}/{prenom}")
    @ResponseBody
    public Contrat affectContratToEtudiant(@RequestBody Contrat c, @PathVariable("nom") String nom, @PathVariable("prenom") String prenom) {
        return  contratService.affectContratToEtudiant(c, nom, prenom);

    }

    @Operation(summary = "Valid Contracts", description = "Nombre de Contrats Valides")
    @GetMapping("/nbrContratsValides/{end}/{start}")
    Integer nbContratsValides(@PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end , @PathVariable("start")@DateTimeFormat(pattern = "yyyy-MM-dd")  Date start) {
        return contratService.nbContratsValides(end,start) ;
    }

    @Operation(summary = "Contracts Expired", description = "Les Contrats Expirées")
    @GetMapping("/contratExp/")
    List<Contrat> contratExp(){
        return contratService.contratExp();
    }

    @Operation(summary = "Get Chiffre Affaire Of Etudiant", description = "getChiffreAffaireParEtudiant")
    @GetMapping("getChiffreAffaireParEtudiant/{id}")
    public float getChiffreAffaireParEtudiant(@PathVariable("id") long id) {
        return contratService.getChiffreAffaireParEtudiant(id);
    }


    @Operation(summary = "Archive Contact", description = "archiverContrat")
    @PutMapping("archiverContrat")
    public void archiverContrat() {

        contratService.archiverContrat();
    }

   /* @Operation(summary = "add And Assign Etudiant To Equipe And Contract", description = "addAndAssignEtudiantToEquipeAndContract")
    @PutMapping(value = "/addAndAssignEtudiantToEquipeAndContract/{idContrat}/{idequipe}")
    @ResponseBody
    public void addAndAssignEtudiantToEquipeAndContract(@RequestBody Etudiant etudiant, @PathVariable("idContrat")long idContrat ,@PathVariable("idequipe") long idequipe ) {
        contratService.addAndAssignEtudiantToEquipeAndContract(etudiant,idContrat,idequipe);
    }*/

}
