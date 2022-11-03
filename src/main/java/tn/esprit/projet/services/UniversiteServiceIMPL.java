package tn.esprit.projet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Universite;
import tn.esprit.projet.repository.UniversiteRepository;

import java.util.List;

@Service
public class UniversiteServiceIMPL implements  IUniversiteService {


    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public List<Universite> getAllUniv() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniv(Universite U) {
        return universiteRepository.save(U);
    }

    @Override
    public Universite updateUni(Universite U) {
        return universiteRepository.save(U);
    }

    @Override
    public void deleteUni(long id) {
        universiteRepository.deleteById(id);

    }

    @Override
    public Universite getUnid(long id) {
        return universiteRepository.findById(id).orElse(null);
    }
}
