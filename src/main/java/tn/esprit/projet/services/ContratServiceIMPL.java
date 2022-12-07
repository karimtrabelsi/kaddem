package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.repository.ContratRepository;
import tn.esprit.projet.repository.EquipeRepository;
import tn.esprit.projet.repository.EtudiantRepository;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class ContratServiceIMPL implements  IContratService{

    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository ur;

    @Autowired
    EquipeRepository er;

    @Override
    public List<Contrat> getAllContrat() {

        return contratRepository.findAll();
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
    public Etudiant findbyname(String prenom) {
        Etudiant e= ur.findEtudiantByPrenom(prenom);
        return e;
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nom, String prenom) {
        Etudiant  etudiant = ur.findByNomAndPrenom(nom,prenom);
            ce.setEtudiant(etudiant);
            contratRepository.save(ce);
        return contratRepository.save(ce);
    }

    @Override
    public Integer nbContratsValides(Date endDate, Date startDate) {
        return contratRepository.countContratByDateDebutContratAfterAndDateFinContratBefore(endDate,startDate);
    }

    @Override
    public List<Contrat> contratExp() {

        return contratRepository.dateExpi();

    }

    @Override
    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, long idContrat, long idEquipe) {
        Contrat contrat = contratRepository.findById(idContrat).get();
        Equipe equipe = er.findById(idEquipe).get();
        e.getContrats().add(contrat);
        e.getEquipes().add(equipe);
        // contrat.setEtudiant(e);
        ur.save(e);
        //etudiantRepository.save(contrat);
        System.out.println(e.getContrats());
        return e ;

    }

    @Scheduled(cron = "*/10 * * * * *")
    public String retrieveStatusContrat()
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
    }
}
