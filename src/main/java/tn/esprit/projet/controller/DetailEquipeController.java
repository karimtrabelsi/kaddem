package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.services.IDetailEquipeService;

import java.util.List;

@RestController
@RequestMapping("DetailEquipe")
@Tag(name ="DetailEquipe" ,description = "Gestion de DetailEquipe ")
public class DetailEquipeController {


    @Autowired
    IDetailEquipeService detailEquipeService;

    @Operation(summary = "Get DetailEquipe", description = "Afficher la liste des DetailEquipe ")
    @GetMapping("/getDetEquipe")
    public List<DetailEquipe> GetDetEqp(){

        return  detailEquipeService.getAlldeqp();
    }

    @Operation(summary = "Add DetailEquipe", description = "Ajouter DetailEquipe ")
    @PostMapping("/addDetEquipe")
    public void  addDetEqp(@RequestBody DetailEquipe D){
      detailEquipeService.adddeqp(D);
    }

    @Operation(summary = "Update DetailEquipe", description = "Modifier DetailEquipe ")
    @PutMapping("/putDetEquipe/{IdEquipe}")
    public void updateDetEqp(@PathVariable("IdEquipe") Long id, @RequestBody DetailEquipe C){

        C.setIdEquipe(id);
        detailEquipeService.updatedeqp(C);
    }

    @Operation(summary = "Delete DetailEquipe", description = "Supprimer DetailEquipe ")
    @DeleteMapping("/delEquipe/{IdEquipe}")
    public  void deletedeqp(@PathVariable("IdEquipe") Long id){

       detailEquipeService.deletedeqp(id);
    }




}
