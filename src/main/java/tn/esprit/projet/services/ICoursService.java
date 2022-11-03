package tn.esprit.projet.services;

import tn.esprit.projet.entities.Cours;

import java.util.List;

public interface ICoursService {

    List<Cours> getAllCours();

    Cours addCours(Cours C);

    Cours updateCours(Cours C);

    void deleteCours(long id);

    Cours getCoursbyid(long id);
}
