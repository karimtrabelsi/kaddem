package tn.esprit.projet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.services.IContratService;
import tn.esprit.projet.services.IDepartementService;

import java.util.List;

@RestController
public class DepartementController {
    @Autowired
    IDepartementService iDepartementService;

    @GetMapping("/getD")
    public List<Departement> GetDep(){

        return  iDepartementService.getAlldep();
    }

    @PostMapping("/addDep")
    public void  addContrat(@RequestBody Departement D){
        iDepartementService.addep(D);
    }


    @PutMapping("/putDep/{idDepart}")
    public void updateC(@PathVariable("idDepart") Long id, @RequestBody Departement C){

        C.setIdDepart(id);
        iDepartementService.updatedep(C);
    }
    @DeleteMapping("/delDep/{idDepart}")
    public  void deleteC(@PathVariable("idDepart") Long id){

        iDepartementService.deletedep(id);
    }
}
