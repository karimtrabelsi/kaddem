package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.*;
import tn.esprit.projet.repository.ContratRepository;
import tn.esprit.projet.repository.CoursRepository;
import tn.esprit.projet.repository.FormationRepository;

import java.util.Date;
import java.util.List;

@Service
public class CoursServiceIMPL implements ICoursService {

    @Autowired
    CoursRepository coursRepository;
    @Autowired
    FormationRepository formationRepository;

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Cours addCours(Cours C) {
        return coursRepository.save(C);
    }

    @Override
    public Cours updateCours(Cours C) {
        return coursRepository.save(C);
    }

    @Override
    public void deleteCours(long id) {
        coursRepository.deleteById(id);
    }

    @Override
    public Cours getCoursbyid(long id) {

        return coursRepository.findById(id).orElse(null);
    }

    @Override
    public Cours affectCoursToFormation(Cours c, String nom, Specialite specialite) {
        Formation formation = formationRepository.findByNomAndSpecialite(nom,specialite);
        c.setFormation(formation);
        coursRepository.save(c);
        return coursRepository.save(c);
    }

    @Override
    public List<Cours>  findBySpecialite(Specialite specialite) {

        return coursRepository.findBySpecialite(specialite);
    }

}
