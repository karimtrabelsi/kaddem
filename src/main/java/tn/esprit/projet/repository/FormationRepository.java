package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.projet.entities.Formation;
import tn.esprit.projet.entities.Specialite;

import java.util.Date;
import java.util.List;


public interface FormationRepository extends JpaRepository<Formation, Long> {

    Formation findByNomAndSpecialite (String nom, Specialite specialite);

    Formation findByNom (String nom);

    @Query("select a from Formation a where a.archive=true")
    List<Formation> findByArchive();


    Formation findByDateformation (Date dateformation);

    Formation findFormationByIdFormation(Long idFormation);


}
