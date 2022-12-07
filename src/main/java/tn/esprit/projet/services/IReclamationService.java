package tn.esprit.projet.services;

import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.entities.Type;

import java.util.List;

public interface IReclamationService {

    void sendMessageWithAttachment(String text,Type objet,String to);

    List<Reclamation> getAllReclamation();


    List<Reclamation> getReclamationByType(Type type);

    Reclamation addReclamation(Reclamation R, String email);

    Reclamation updateReclamation(Reclamation R);
    void deleteReclamation(long id);
    Reclamation getReclamationbyid(long id);

    Reclamation confirmReclamation(long idReclamation);
}
