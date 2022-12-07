package tn.esprit.projet.services;

import tn.esprit.projet.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversite();

    Universite addUniversite (Universite Un);

    void deleteUniversite (Integer id);

    Universite updateUniversite (Universite Un);

    Universite retrieveUniversite (Integer id);
    void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement);
    Integer getUniversiteByDepartementNumber(Integer idUniv);
}
