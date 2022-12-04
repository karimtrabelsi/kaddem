package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.projet.entities.Etudiant;
import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    //Afficher la luste des étudiants departement "Informatique" et université "Esprit"


}
