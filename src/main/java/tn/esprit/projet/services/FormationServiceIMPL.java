package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Formation;
import tn.esprit.projet.repository.FormationRepository;

import java.util.List;

@Service
public class FormationServiceIMPL implements IFormationService {

    @Autowired
    FormationRepository FormationRepository;

    @Override
    public List<Formation> getAllFormation() {
        return FormationRepository.findAll();
    }

    @Override
    public Formation addFormation(Formation F) {
        return FormationRepository.save(F);
    }

    @Override
    public Formation updateFormation(Formation F) {
        return FormationRepository.save(F);
    }

    @Override
    public void deleteFormation(long id) {
        FormationRepository.deleteById(id);
    }

    @Override
    public Formation getFormationbyid(long id) {

        return FormationRepository.findById(id).orElse(null);
    }
}
