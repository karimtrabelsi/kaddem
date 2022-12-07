package tn.esprit.projet.services;

import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Specialite;

import java.util.Date;
import java.util.List;

public interface IContratService {

    List<Contrat> getAllContrat();

    List<Contrat> getAllContratArchive();

    Contrat addContrat(Contrat C);
    Contrat updateContrat(Contrat C);
    void deleteContrat(long id);
    Contrat getContratbyid(long id);

    List<Contrat> getContratBySpecialite(Specialite specialite);

    Contrat affectContratToEtudiant (Contrat ce, String nom, String prenom);

    Integer nbContratsValides(Date endDate, Date startDate);

    List<Contrat> contratExp();


    float getChiffreAffaireParEtudiant(long idEtudiant);

    //String retrieveStatusContrat();

    //public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, long idContrat,long idEquipe);

    void archiverContrat();
}
