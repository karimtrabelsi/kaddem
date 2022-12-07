// Importing packages
package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.repository.DepartementRepository;
// Importing required classes
import java.util.List;


// Class
@Service
public class DepartementServiceIMPL implements IDepartementService {


    @Autowired
    DepartementRepository departementRepository;

    // Read operation
    @Override
    public List<Departement> getAlldep() {
        return departementRepository.findAll();
    }
    // Add operation
    @Override
    public Departement addep(Departement D) {
        return departementRepository.save(D);
    }

    // Update operation
    @Override
    public Departement updatedep(Departement D) {
        return departementRepository.save(D);
    }

    // Delete operation
    @Override
    public void deletedep(long id) {
       departementRepository.deleteById(id);
    }

    @Override
    public Departement getdepbyid(long id) {
        return departementRepository.findById(id).orElse(null);
    }
}
