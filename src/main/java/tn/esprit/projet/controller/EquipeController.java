package tn.esprit.projet.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.services.IEquipeService;

import java.util.List;

@RestController
@RequestMapping("Equipe")
@Tag(name ="Equipe" ,description = "Gestion des Equipes ")
public class EquipeController {

    @Autowired
    IEquipeService iEquipeService;

    @Operation(summary = "Get Equipe", description = "Afficher la liste des Equipes ")
    @GetMapping("/Eqp")
    public List<Equipe> Geteqp(){

        return  iEquipeService.getAlleqp();
    }

    @Operation(summary = "Add Equipe", description = "Ajouter des Equipes ")
    @PostMapping("/addEquipe")
    public void  addEqp(@RequestBody Equipe D){

        iEquipeService.addeqp(D);
    }

    @Operation(summary = "Update Equipe", description = "Modifier des Equipes ")
    @PutMapping("/putEquipe/{IdEquipe}")
    public void updateEqp(@PathVariable("IdEquipe") Long id, @RequestBody Equipe C){

        C.setIdEquipe(id);
       iEquipeService.updateeqp(C);
    }

    @Operation(summary = "Delete Equipe", description = "Modifier des Equipes ")
    @DeleteMapping("/delDetEquipe/{IdEquipe}")
    public  void deleteEqp(@PathVariable("IdEquipe") Long id){

        iEquipeService.deleteeqp(id);
    }
}
