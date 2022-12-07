package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Cours;
import tn.esprit.projet.entities.Formation;
import tn.esprit.projet.entities.Specialite;

import java.util.Date;
import java.util.List;


public interface CoursRepository extends JpaRepository<Cours, Long> {
   // public List<Cours> findByNomCAndSpecialte(String NomCours, Specialite specialite);

    List<Cours> findBySpecialite (Specialite specialite);



}
