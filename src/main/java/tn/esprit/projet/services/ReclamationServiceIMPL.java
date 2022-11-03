package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.repository.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationServiceIMPL implements  IReclamationService{

    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public List<Reclamation> getAllReclamation() {

        return reclamationRepository.findAll();
    }

    @Override
    public Reclamation addReclamation(Reclamation R) {

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
