package tn.esprit.projet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.IContratService;

import java.util.List;

@RestController
public class ContratController {
    @Autowired
    IContratService contratService;

    @GetMapping("/getC")
    public List<Contrat> GetC(){

        return  contratService.getAllContrat();
    }

    @PostMapping("/addC")
    public void  addContrat(@RequestBody Contrat C){
        contratService.addContrat(C);
    }


    @PutMapping("/putC/{idContrat}")
    public void updateC(@PathVariable("idContrat") Long id, @RequestBody Contrat C){

        C.setIdContrat(id);
        contratService.updateContrat(C);
    }
    @DeleteMapping("/delC/{idContrat}")
    public  void deleteC(@PathVariable("idContrat") Long id){

        contratService.deleteContrat(id);
    }
}
