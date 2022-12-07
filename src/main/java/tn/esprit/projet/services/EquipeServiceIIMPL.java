package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Equipe;
import tn.esprit.projet.repository.EquipeRepository;

import java.util.List;

@Service
public class EquipeServiceIIMPL implements  IEquipeService{


    @Autowired
    EquipeRepository equipeRepository;

    @Override
    public List<Equipe> getAlleqp() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe addeqp(Equipe E) {
        return equipeRepository.save(E);
    }

    @Override
    public Equipe updateeqp(Equipe E) {
        return equipeRepository.save(E);
    }

    @Override
    public void deleteeqp(long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public Equipe geteqbyid(long id) {
        return equipeRepository.findById(id).orElse(null);
    }
}
