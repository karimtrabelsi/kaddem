package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.DetailEquipe;
import tn.esprit.projet.repository.DetailEquipeRepository;

import java.util.List;

@Service

public class DetailEquipeServiceIMPL  implements  IDetailEquipeService{
    @Autowired
    DetailEquipeRepository detailEquipeRepository;

    @Override
    public List<DetailEquipe> getAlldeqp() {
        return detailEquipeRepository.findAll();
    }

    @Override
    public DetailEquipe adddeqp(DetailEquipe E) {
        return detailEquipeRepository.save(E);
    }

    @Override
    public DetailEquipe updatedeqp(DetailEquipe E) {
        return detailEquipeRepository.save(E);
    }

    @Override
    public void deletedeqp(long id) {
        detailEquipeRepository.deleteById(id);

    }

    @Override
    public DetailEquipe getdeqpbyid(long id) {
        return detailEquipeRepository.findById(id).orElse(null);
    }
}
