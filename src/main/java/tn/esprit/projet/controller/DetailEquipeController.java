package tn.esprit.projet.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.services.IDetailEquipeService;

import java.util.List;

@RestController

public class DetailEquipeController {


    @Autowired
    IDetailEquipeService detailEquipeService;

    @GetMapping("/getDetEquipe")
    public List<DetailEquipe> GetDetEqp(){

        return  detailEquipeService.getAlldeqp();
    }

    @PostMapping("/addDetEquipe")
    public void  addDetEqp(@RequestBody DetailEquipe D){
      detailEquipeService.adddeqp(D);
    }


    @PutMapping("/putDetEquipe/{IdEquipe}")
    public void updateDetEqp(@PathVariable("IdEquipe") Long id, @RequestBody DetailEquipe C){

        C.setIdEquipe(id);
        detailEquipeService.updatedeqp(C);
    }
    @DeleteMapping("/delDetEquipe/{IdEquipe}")
    public  void deleteDetEqp(@PathVariable("IdEquipe") Long id){

       detailEquipeService.deletedeqp(id);
    }




}
