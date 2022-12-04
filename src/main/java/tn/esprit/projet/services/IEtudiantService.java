package tn.esprit.projet.services;

import tn.esprit.projet.entities.Etudiant;

import java.util.List;


public interface IEtudiantService {

    List<Etudiant> getAllEtudiant();
    Etudiant addEtudiant(Etudiant E);
    Etudiant updateEtudiant(Etudiant E);
    void deleteEtudiant(long id);
    Etudiant getEtudiantbyid(long id);
    void assignEtudiantToDepartement(Integer etudiantId,Integer departementId);

}
