package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.*;
import tn.esprit.projet.repository.ContratRepository;
import tn.esprit.projet.repository.EquipeRepository;
import tn.esprit.projet.repository.EtudiantRepository;
import tn.esprit.projet.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class ContratServiceIMPL implements  IContratService{

    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository er;

    @Autowired
    UserRepository ur;

    @Autowired
    EquipeRepository eqr;

    @Override
    public List<Contrat> getAllContrat() {

        return contratRepository.findAll();
    }

    @Override
    public List<Contrat> getAllContratArchive() {

        return contratRepository.ContratArchive();
    }


    @Override
    public Contrat addContrat(Contrat C) {

        return contratRepository.save(C);

    }

    @Override
    public Contrat updateContrat(Contrat C) {

        return contratRepository.save(C);
    }

    @Override
    public void deleteContrat(long id) {

        contratRepository.deleteById(id);
    }

    @Override
    public Contrat getContratbyid(long id) {

        return contratRepository.findById(id).orElse(null);
    }


    @Override
    public List<Contrat> getContratBySpecialite(Specialite specialite) {
        return contratRepository.findBySpecialite(specialite);
    }

    /*@Override
    public Contrat affectContratToEtudiant(Contrat ce, String nom, String prenom) {
        Etudiant  etudiant = ur.findByNomAndPrenom(nom,prenom);
            ce.setEtudiant(etudiant);
            contratRepository.save(ce);
        return contratRepository.save(ce);
    }*/
    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nom, String prenom) {

        User user = ur.findByFirstNameAndLastName(nom,prenom);
        if (user == null) {
            System.out.println("User does not exist");
        } else if (contratRepository.findByUserIdAndAndArchive(user.getId(), false).size() < 5) {
            ce.setUser(user);

            return contratRepository.save(ce);
        }
        return null;
    }

    @Override
    public Integer nbContratsValides(Date endDate, Date startDate) {
        return contratRepository.countContratByDateDebutContratAfterAndDateFinContratBefore(endDate,startDate);
    }

    @Override
    public List<Contrat> contratExp() {

        return contratRepository.dateExpi();

    }

    /*@Override
    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, long idContrat, long idEquipe) {
        Contrat contrat = contratRepository.findById(idContrat).get();
        Equipe equipe = eqr.findById(idEquipe).get();
        e.getContrats().add(contrat);
        e.getEquipes().add(equipe);
        // contrat.setEtudiant(e);
        ur.save(e);
        //etudiantRepository.save(contrat);
        System.out.println(e.getContrats());
        return e ;

    }*/

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
    @Override
    public float getChiffreAffaireParEtudiant(long id) {
        float ca = 0;
        long nbrMois;
        List<Contrat> contrats = contratRepository.findByUserIdAndArchive(id, false);
        for (Contrat c : contrats) {
            nbrMois = ChronoUnit.MONTHS.between(convertToLocalDateViaInstant(c.getDateDebutContrat()), convertToLocalDateViaInstant(c.getDateFinContrat()));
            ca += c.getMontant() * nbrMois;
        }
        return ca;
    }


    @Override
    public void archiverContrat() {
        List<Contrat> c = contratRepository.dateExpin15();
        for(Contrat contrat :c){
        contrat.setArchive(true);
        contrat.setUser(null);
        contratRepository.save(contrat);}
    }

    // @Scheduled(cron = "*/10 * * * * *")
    /*public String retrieveStatusContrat()
    {
        List<Contrat> contrats=contratRepository.dateExpin15();
        String string = "les contrats concernés tous les 15 jours :";
        for (Contrat c : contrats){
            string=string+"contrat id :"+c.getIdContrat()+"\n";
            string=string+"contrat date fin :"+c.getDateFinContrat()+"\n";
            string=string+"contrat debut date "+c.getDateDebutContrat()+"\n";
            string =string+"specialité"+c.getSpecialite()+"\n";
        }
        System.out.println(string);
        return string;
    }*/

}
