package tn.esprit.projet.services;

import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Departement;

import java.util.List;

public interface IDepartementService {


    List<Departement> getAlldep();
    Departement addep(Departement D);
    Departement updatedep(Departement D);
    void deletedep(long id);
    Departement getdepbyid(long id);
}
