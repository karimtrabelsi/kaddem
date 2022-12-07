package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.DetailEquipe;

public interface DetailEquipeRepository extends JpaRepository<DetailEquipe,Long> {
}
