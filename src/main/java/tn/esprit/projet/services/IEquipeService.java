package tn.esprit.projet.services;

import tn.esprit.projet.entities.Equipe;

import java.util.List;

public interface IEquipeService {
    List<Equipe> getAlleqp();
    Equipe addeqp(Equipe E);
    Equipe updateeqp(Equipe E);
    void deleteeqp(long id);
    Equipe geteqbyid(long id);
}
