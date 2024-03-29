package tn.esprit.projet.services;

import tn.esprit.projet.entities.Contrat;

import java.util.List;

public interface IContratService {

    List<Contrat> getAllContrat();
    Contrat addContrat(Contrat C);
    Contrat updateContrat(Contrat C);
    void deleteContrat(long id);
    Contrat getContratbyid(long id);
}
