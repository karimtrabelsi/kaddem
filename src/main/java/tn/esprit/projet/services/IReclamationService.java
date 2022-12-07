package tn.esprit.projet.services;

import tn.esprit.projet.entities.Reclamation;

import java.util.List;

public interface IReclamationService {

    void sendMessageWithAttachment(String text,String to);

    List<Reclamation> getAllReclamation();


    Reclamation addReclamation(Reclamation R, String email);

    Reclamation updateReclamation(Reclamation R);
    void deleteReclamation(long id);
    Reclamation getReclamationbyid(long id);
}
