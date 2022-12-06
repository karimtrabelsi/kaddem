package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.repository.EtudiantRepository;
import tn.esprit.projet.repository.ReclamationRepository;

import java.util.List;
import java.util.Properties;

@Service
public class ReclamationServiceIMPL implements  IReclamationService{

    @Autowired
    ReclamationRepository reclamationRepository;

    @Autowired
    EtudiantRepository ur;
    @Autowired
    private JavaMailSender emailSender;



    @Override
    public void sendMessageWithAttachment(String text,String to){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testahmedahmed210@gmail.com");
        message.setTo(to);
        message.setSubject("Reclamation Body");
        message.setText(text);
        emailSender.send(message);
        System.out.println("Mail sent successfully !");
    }



    @Override
    public List<Reclamation> getAllReclamation() {

        return reclamationRepository.findAll();


    }

    @Override
    public Reclamation addReclamation(Reclamation R, String email) {
        Etudiant etudiant = ur.findByEmail(email);
        R.setEtudiant(etudiant);
        reclamationRepository.save(R);
        return reclamationRepository.save(R);
    }


    @Override
    public Reclamation updateReclamation(Reclamation R) {

        return reclamationRepository.save(R);
    }

    @Override
    public void deleteReclamation(long id) {

        reclamationRepository.deleteById(id);
    }

    @Override
    public Reclamation getReclamationbyid(long id) {

        return reclamationRepository.findById(id).orElse(null);
    }
}
