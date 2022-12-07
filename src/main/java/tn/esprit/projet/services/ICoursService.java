package tn.esprit.projet.services;

import tn.esprit.projet.entities.Cours;
import tn.esprit.projet.entities.Formation;
import tn.esprit.projet.entities.Specialite;

import java.util.List;

public interface ICoursService {

    List<Cours> getAllCours();

    Cours addCours(Cours C);

    Cours updateCours(Cours C);

    void deleteCours(long id);

    Cours getCoursbyid(long id);

    Cours affectCoursToFormation(Cours c, String Nom, Specialite specialite);

    List<Cours>  findBySpecialite(Specialite specialite);}
