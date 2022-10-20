package tn.esprit.projet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Universite;
import tn.esprit.projet.services.IUniversiteService;

import java.util.List;

@RestController
public class UniversiteController {


    @Autowired
    IUniversiteService iUniversiteService;

    @GetMapping("/allUni")
    public List<Universite> GetUni(){

        return  iUniversiteService.getAllUniv();
    }
    @PostMapping("/addUni")
    public void  addUniv(@RequestBody Universite D){
       iUniversiteService.addUniv(D);
    }


    @PutMapping("/putUni/{idUni}")
    public void updateUni(@PathVariable("idUni") Long id, @RequestBody Universite E){

        E.setIdUni(id);
        iUniversiteService.updateUni(E);
    }
    @DeleteMapping("/delUni/{idUni}")
    public  void deleteUni(@PathVariable("idUni") Long id){

     iUniversiteService.deleteUni(id);
    }
}
