package tn.esprit.projet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.TriunivDto;
import tn.esprit.projet.entities.Universite;

public interface UniversiteRepository extends JpaRepository<Universite,Integer> {
	
	@Query(" select u.departements from Universite u where u.id=:id")
	List<Departement> getUniversiteByDepartementNumber(@Param("id") Integer id);

	//http://localhost:8088/SpringMVC/servlet/depEtudUniv/3
	@Query(value = "SELECT COUNT(e.id) FROM users as e \n" +
			"LEFT JOIN departement ON departement.id_depart=e.departement_id_depart \n" +
			"LEFT JOIN universite_departements ON departements_id_depart=departement.id_depart \n"+
			"LEFT JOIN universite ON universite.id=universite_departements.universite_id \n" +
			"WHERE universite.id= :id", nativeQuery = true)
	int nbretudUniv(@Param("id") Integer id);

	@Query(value = "SELECT    universite.nom_univ univ, COUNT(users.id)  nbr FROM users\n" +
			"LEFT JOIN departement ON departement.id_depart=users.departement_id_depart \n" +
			"LEFT JOIN universite_departements ON departements_id_depart=departement.id_depart \n" +
			"LEFT JOIN universite ON universite.id = universite_departements.universite_id \n" +
			"GROUP BY(universite.id) \n" +
			"ORDER BY(COUNT(users.id) ) DESC;", nativeQuery = true)
	public List<TriunivDto> triuniv();
}
