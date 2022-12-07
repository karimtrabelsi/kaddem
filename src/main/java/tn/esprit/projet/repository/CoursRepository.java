package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Cours;


public interface CoursRepository extends JpaRepository<Cours, Long> {
}
