package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.EtudiantServiceIMPL;
import tn.esprit.projet.services.IEtudiantService;

import java.util.List;

@Tag(name = "Gestion D'etudiants")
@RestController
@RequestMapping("etudiant")
public class EtudiantController {

    @Autowired
    IEtudiantService etudiantService;

    @Operation(summary = "Get one etudiant", description = "Afficher un etudiant specifique par id")
    @GetMapping("/getone/{idEtudiant}")
    public Etudiant getEtudiantbyid(@PathVariable("idEtudiant") Long id){

        return  etudiantService.getEtudiantbyid(id);
    }
    @Operation(summary = "Get all etudiants", description = "Afficher tous les etudiants")
    @GetMapping("/getall")
    public List<Etudiant>GetEtudiant(){

        return  etudiantService.getAllEtudiant();
    }

    @Operation(summary = "Add etudiant", description = "Ajouter un nouveau etudiant")
    @PostMapping("/addetude")
    public void addEtud(@RequestBody Etudiant E){

        etudiantService.addEtudiant(E);
    }
    @Operation(summary = "Update etudiant", description = "Mettre a jours un etudiant")
    @PutMapping("/put/{idEtudiant}")
    public void updateEtud(@PathVariable("idEtudiant") Long id, @RequestBody Etudiant E){

        E.setIdEtudiant(id);
        etudiantService.updateEtudiant(E);
    }

    @Operation(summary = "Delete one etudiant", description = "Supprimer un etudiant specifique par id")
    @DeleteMapping("/del/{idEtudiant}")
    public  void deleteEtud(@PathVariable("idEtudiant") Long id){

        etudiantService.deleteEtudiant(id);
    }



}
