package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.projet.entities.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    Etudiant findEtudiantByPrenom(String prenom);
    @Query(
            value = "select nom,prenom from Etudiant e where e.nom=?1 and e.prenom=?2",
            nativeQuery = true)
    public Etudiant findEtudiantByNomPrenom(String nom,String prenom);

    public Etudiant findByNomAndPrenom(String nom, String prenom);

    public Etudiant findByEmail(String email);
    @Query(
            value = "select nom from Etudiant e where e.nom=?1",
            nativeQuery = true)
    public Etudiant findEtudiantByNom(String nom);
  /*  @Query(value = "select etudiant from Etudiant etudiant " +
            "join Departement departement on etudiant.departement.idDepart=departement.idDepart " +
            "join Universite universite on ")
    List<Etudiant> retrieveEtudiantsInfoEsprit(@Param("nomDepar")String dep, @Param("nomUniv")String universite);
   */
}
