package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.repository.ContratRepository;

import java.util.List;

@Service
public class ContratServiceIMPL implements  IContratService{

    @Autowired
    ContratRepository contratRepository;

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
}
