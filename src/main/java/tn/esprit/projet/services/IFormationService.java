package tn.esprit.projet.services;

import tn.esprit.projet.entities.Formation;

import java.util.Date;
import java.util.List;

public interface IFormationService {

    List<Formation> getAllFormation();

    Formation addFormation(Formation F);

    Formation updateFormation(Formation F);

    void deleteFormation(long id);

    Formation getFormationbyid(long id);

    Formation archiverFormation(long idFormation);



    List<Formation> findByArchive();

    Formation findByDate(Date dateformation) ;


    int countEtudiantByFormation(Long idFormation);
}




