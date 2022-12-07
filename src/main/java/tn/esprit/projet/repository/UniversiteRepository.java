package tn.esprit.projet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.Universite;

public interface UniversiteRepository extends JpaRepository<Universite,Integer> {
	
	@Query(" select u.departements from Universite u where u.id=:id")
	List<Departement> getUniversiteByDepartementNumber(@Param("id") Integer id);
}
