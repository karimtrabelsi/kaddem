package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.*;
import tn.esprit.projet.repository.ReclamationRepository;
import tn.esprit.projet.repository.UserRepository;

import java.util.List;

@Service
public class ReclamationServiceIMPL implements  IReclamationService{

    @Autowired
    ReclamationRepository reclamationRepository;


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    UserRepository ur;



    @Override
    public void sendMessageWithAttachment(String nom,Type objet,String to){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testahmedahmed210@gmail.com");
        message.setTo(to);
        message.setSubject("Reclamation");
        message.setText("Bonjour Mr/Mme "+nom+
                " Nous avons bien reçu votre demande concernant la probleme <" + objet + ">"+
                " Nous sommes sincèrement désolés pour ce désagrément. Nous mettons tout en œuvre pour résoudre ce " +
                "problème au plus vite.");
        emailSender.send(message);
        System.out.println("Mail sent successfully !");
    }



    @Override
    public List<Reclamation> getAllReclamation() {

        return reclamationRepository.findAll();


    }

    @Override
    public List<Reclamation> getReclamationByType(Type type) {
        return reclamationRepository.findByType(type);
    }

    @Override
    public Reclamation addReclamation(Reclamation R, String email) {
        User user = ur.findByEmail(email);
        R.setUser(user);
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

    @Override
    public Reclamation confirmReclamation(long idReclamation) {
        Reclamation c = reclamationRepository.findById(idReclamation).orElse(null);
        c.setStatut(true);
        return reclamationRepository.save(c);
    }

}
