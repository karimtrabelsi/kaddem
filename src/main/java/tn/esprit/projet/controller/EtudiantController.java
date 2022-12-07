package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.EtudiantServiceIMPL;
import tn.esprit.projet.services.IEtudiantService;

import java.util.List;


@RestController
@RequestMapping("Etudiant")
@Tag(name ="Etudiant" ,description = "Gestion des Etudiants ")
public class EtudiantController {

    @Autowired
    IEtudiantService etudiantService;

    @Operation(summary = "Get Etudiant By Id", description = "Afficher des Etudiants ")
    @GetMapping("/getone/{idEtudiant}")
    public Etudiant getEtudiantbyid(@PathVariable("idEtudiant") Long id){

        return  etudiantService.getEtudiantbyid(id);
    }

    @Operation(summary = "Get Etudiant", description = "Afficher liste des Etudiants ")
    @GetMapping("/getall")
    public List<Etudiant>GetEtudiant(){

        return  etudiantService.getAllEtudiant();
    }

    @Operation(summary = "Add Etudiant", description = "Ajouter des Etudiants ")
    @PostMapping("/addetude")
    public void addEtud(@RequestBody Etudiant E){

        etudiantService.addEtudiant(E);
    }

    @Operation(summary = "Update Etudiant", description = "Modifier des Etudiants ")
    @PutMapping("/put/{idEtudiant}")
    public void updateEtud(@PathVariable("idEtudiant") Long id, @RequestBody Etudiant E){

        E.setIdEtudiant(id);
        etudiantService.updateEtudiant(E);
    }

    @Operation(summary = "Delete Etudiant", description = "Supprimer des Etudiants ")
    @DeleteMapping("/del/{idEtudiant}")
    public  void deleteEtud(@PathVariable("idEtudiant") Long id){

        etudiantService.deleteEtudiant(id);
    }



}
