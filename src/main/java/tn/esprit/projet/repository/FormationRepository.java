package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Formation;


public interface FormationRepository extends JpaRepository<Formation, Long> {
}
