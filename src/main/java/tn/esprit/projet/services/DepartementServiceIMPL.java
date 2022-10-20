package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.repository.DepartementRepository;

import java.util.List;

@Service
public class DepartementServiceIMPL implements IDepartementService {


    @Autowired
    DepartementRepository departementRepository;


    @Override
    public List<Departement> getAlldep() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addep(Departement D) {
        return departementRepository.save(D);
    }

    @Override
    public Departement updatedep(Departement D) {
        return departementRepository.save(D);
    }

    @Override
    public void deletedep(long id) {
       departementRepository.deleteById(id);

    }

    @Override
    public Departement getdepbyid(long id) {
        return departementRepository.findById(id).orElse(null);
    }
}
