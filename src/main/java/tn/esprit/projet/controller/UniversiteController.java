package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Universite;
import tn.esprit.projet.services.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("Universite")
@Tag(name ="Universite" ,description = "Gestion des Universites ")
public class
UniversiteController {


    @Autowired
    IUniversiteService iUniversiteService;

    @Operation(summary = "Get Universite", description = "Afficher la liste des Universites ")
    @GetMapping("/allUni")
    public List<Universite> GetUni(){

        return  iUniversiteService.getAllUniv();
    }

    @Operation(summary = "Add Universite", description = "Ajouter Universites ")
    @PostMapping("/addUni")
    public void  addUniv(@RequestBody Universite D){
       iUniversiteService.addUniv(D);
    }


    @Operation(summary = "Update Universite", description = "Modiifer Universites ")
    @PutMapping("/putUni/{idUni}")
    public void updateUni(@PathVariable("idUni") Long id, @RequestBody Universite E){

        E.setIdUni(id);
        iUniversiteService.updateUni(E);
    }

    @Operation(summary = "Delete Universite", description = "Supprimer Universites ")
    @DeleteMapping("/delUni/{idUni}")
    public  void deleteUni(@PathVariable("idUni") Long id){

     iUniversiteService.deleteUni(id);
    }
}
