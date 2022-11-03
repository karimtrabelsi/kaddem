package tn.esprit.projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.services.IEquipeService;

import java.util.List;

@RestController
public class EquipeController {

    @Autowired
    IEquipeService iEquipeService;

    @GetMapping("/Eqp")
    public List<Equipe> Geteqp(){

        return  iEquipeService.getAlleqp();
    }
    @PostMapping("/addEquipe")
    public void  addEqp(@RequestBody Equipe D){
       iEquipeService.addeqp(D);
    }


    @PutMapping("/putEquipe/{IdEquipe}")
    public void updateEqp(@PathVariable("IdEquipe") Long id, @RequestBody Equipe C){

        C.setIdEquipe(id);
       iEquipeService.updateeqp(C);
    }

}
