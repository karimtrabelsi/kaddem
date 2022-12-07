package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.projet.entities.*;

import java.util.List;


public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {

    List<Reclamation> findByType(Type type);

}
