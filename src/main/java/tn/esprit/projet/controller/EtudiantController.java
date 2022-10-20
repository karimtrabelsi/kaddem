package tn.esprit.projet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.services.EtudiantServiceIMPL;
import tn.esprit.projet.services.IEtudiantService;

import java.util.List;


@RestController
public class EtudiantController {

    @Autowired
    IEtudiantService etudiantService;

    @GetMapping("/getone/{idEtudiant}")
    public Etudiant getEtudiantbyid(@PathVariable("idEtudiant") Long id){

        return  etudiantService.getEtudiantbyid(id);
    }
    @GetMapping("/getall")
    public List<Etudiant>GetEtudiant(){

        return  etudiantService.getAllEtudiant();
    }
    @PostMapping("/addetude")
    public void addEtud(@RequestBody Etudiant E){

        etudiantService.addEtudiant(E);
    }
    @PutMapping("/put/{idEtudiant}")
    public void updateEtud(@PathVariable("idEtudiant") Long id, @RequestBody Etudiant E){

        E.setIdEtudiant(id);
        etudiantService.updateEtudiant(E);
    }
    @DeleteMapping("/del/{idEtudiant}")
    public  void deleteEtud(@PathVariable("idEtudiant") Long id){

        etudiantService.deleteEtudiant(id);
    }



}
