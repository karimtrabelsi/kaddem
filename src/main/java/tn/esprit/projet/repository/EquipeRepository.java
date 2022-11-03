package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe,Long> {
}
