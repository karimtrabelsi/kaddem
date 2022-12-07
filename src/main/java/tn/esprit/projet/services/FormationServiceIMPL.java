package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Formation;
import tn.esprit.projet.repository.FormationRepository;

import java.util.Date;
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

    @Override
    public Formation archiverFormation(long idFormation) {
        Formation f = FormationRepository.findById(idFormation).orElse(null);
        f.setArchive(true);
        return FormationRepository.save(f);
    }

    @Override
    public List<Formation> findByArchive() {
        return FormationRepository.findByArchive();
    }

    @Override
    public Formation findByDate(Date dateformation) {

        return FormationRepository.findByDateformation(dateformation);
    }

    @Override
    public int countEtudiantByFormation(Long idFormation) {
        Formation formation = FormationRepository.findFormationByIdFormation( idFormation);
        return formation.getUsers().size();
    }


}


