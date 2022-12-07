package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.services.IDepartementService;

import java.util.List;

@RestController
@RequestMapping("Departement")
@Tag(name ="Departement" ,description = "Gestion de Departements ")
public class DepartementController {
    @Autowired
    IDepartementService iDepartementService;

    @Operation(summary = "Get AllDepartement", description = "Afficher la liste des Departements ")
    @GetMapping("/getD")
    public List<Departement> GetDep(){

        return  iDepartementService.getAlldep();
      //  return iDepartmentService.fetchDepartmentList();
    }

    @Operation(summary = "Add Departement", description = "Ajouter des Departements ")
    @PostMapping("/addDep")
    public void  addDep(@RequestBody Departement D){
        iDepartementService.addep(D);
    }

    @Operation(summary = "Update Departement", description = "Modifier des Departements ")
    @PutMapping("/putDep/{idDepart}")
    public void updateDep(@PathVariable("idDepart") Long id, @RequestBody Departement C){

     // return iDepartementService.updatedep(C,id) ;
        C.setIdDepart(id);
        iDepartementService.updatedep(C);
    }

    @Operation(summary = "Delete Departement", description = "Supprimer des Departements ")
    @DeleteMapping("/delDep/{idDepart}")
    public  String deleteDep(@PathVariable("idDepart") Long id){

        iDepartementService.deletedep(id);
        return "Deleted Successfully";

    }
}
