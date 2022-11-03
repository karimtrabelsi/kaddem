package tn.esprit.projet.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Formation;
import tn.esprit.projet.services.IFormationService;

import java.util.List;

@RestController
@RequestMapping("Formation")
@Tag(name ="Formation" ,description = "Gestion des Formations ")
public class FormationController {
    @Autowired
    IFormationService FormationService;

    @Operation(summary = "Get Formation", description = "Afficher liste des Formations ")
    @GetMapping("/getF")
    public List<Formation> GetF() {

        return FormationService.getAllFormation();
    }

    @Operation(summary = "Add Formation", description = "Ajouter des Formations ")
    @PostMapping("/addF")
    public void addFormation(@RequestBody Formation F) {
        FormationService.addFormation(F);
    }

    @Operation(summary = "Update Formation", description = "Modiifer des Formations ")
    @PutMapping("/putF/{idFormation}")
    public void updateC(@PathVariable("idFormation") Long id, @RequestBody Formation F) {

        F.setIdFormation(id);
        FormationService.updateFormation(F);
    }

    @Operation(summary = "Delete Formation", description = "Supprimer des Formations ")
    @DeleteMapping("/delF/{idFormation}")
    public void deleteF(@PathVariable("idFormation") Long id) {

        FormationService.deleteFormation(id);
    }
}
