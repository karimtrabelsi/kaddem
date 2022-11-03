package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Cours;
import tn.esprit.projet.services.ICoursService;

import java.util.List;

@RestController
@RequestMapping("Cours")
@Tag(name ="Cours" ,description = "Gestion de Cours ")
public class CoursController {
    @Autowired
    ICoursService CoursService;

    @Operation(summary = "Get AllCours", description = "Afficher la liste des Cours ")
    @GetMapping("/getCr")
    public List<Cours> GetC() {

        return CoursService.getAllCours();
    }

    @Operation(summary = "Add AllCours", description = "Ajouter des Cours ")
    @PostMapping("/addCr")
    public void addCours(@RequestBody Cours C) {
        CoursService.addCours(C);
    }


    @Operation(summary = "Update AllCours", description = "Modifier des Cours ")
    @PutMapping("/putCr/{idCours}")
    public void updateC(@PathVariable("idCours") Long id, @RequestBody Cours C) {

        C.setIdCours(id);
        CoursService.updateCours(C);
    }

    @Operation(summary = "Delete AllCours", description = "Supprimer des Cours ")
    @DeleteMapping("/delCr/{idCours}")
    public void deleteC(@PathVariable("idCours") Long id) {

        CoursService.deleteCours(id);
    }
}
