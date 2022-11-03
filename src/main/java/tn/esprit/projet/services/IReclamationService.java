package tn.esprit.projet.services;

import tn.esprit.projet.entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    List<Reclamation> getAllReclamation();
    Reclamation addReclamation(Reclamation R);
    Reclamation updateReclamation(Reclamation R);
    void deleteReclamation(long id);
    Reclamation getReclamationbyid(long id);
}
