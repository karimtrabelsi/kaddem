package tn.esprit.projet.services;

import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Etudiant;

import java.util.Date;
import java.util.List;

public interface IContratService {

    List<Contrat> getAllContrat();
    Contrat addContrat(Contrat C);
    Contrat updateContrat(Contrat C);
    void deleteContrat(long id);
    Contrat getContratbyid(long id);
    Etudiant findbyname(String prenom);
    Contrat affectContratToEtudiant (Contrat ce, String nom, String prenom);

    Integer nbContratsValides(Date endDate, Date startDate);

    List<Contrat> contratExp();

    String retrieveStatusContrat();

    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, long idContrat,long idEquipe);

}
