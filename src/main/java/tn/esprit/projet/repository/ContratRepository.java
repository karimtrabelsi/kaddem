package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.projet.entities.Contrat;

import java.util.Date;
import java.util.List;


public interface ContratRepository extends JpaRepository<Contrat,Long> {

    @Query("select  count (c) from Contrat c where c.DateFinContrat<=?1 and c.DateDebutContrat>=?2")
    Integer countContratByDateDebutContratAfterAndDateFinContratBefore(Date DateFinContrat, Date DateDebutContrat);

    @Query("select  c from Contrat c where DATEDIFF(current_date,c.DateFinContrat)>1")
    List<Contrat> dateExpi();

    @Query("select  c from Contrat c where DATEDIFF(current_date,c.DateFinContrat)=-15")
    List<Contrat> dateExpin15();
    @Query("select c from Contrat c where DATEDIFF(c.DateFinContrat,c.DateDebutContrat)>=365")
    List<Contrat> contratDepasseAn();

}
