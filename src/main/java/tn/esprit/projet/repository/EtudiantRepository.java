package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

}
