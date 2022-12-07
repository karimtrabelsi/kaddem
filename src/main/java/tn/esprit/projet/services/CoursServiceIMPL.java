package tn.esprit.projet.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Cours;
import tn.esprit.projet.repository.CoursRepository;

import java.util.List;

@Service
public class CoursServiceIMPL implements ICoursService {

    @Autowired
    CoursRepository coursRepository;

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Cours addCours(Cours C) {
        return coursRepository.save(C);
    }

    @Override
    public Cours updateCours(Cours C) {
        return coursRepository.save(C);
    }

    @Override
    public void deleteCours(long id) {
        coursRepository.deleteById(id);
    }

    @Override
    public Cours getCoursbyid(long id) {

        return coursRepository.findById(id).orElse(null);
    }
}
