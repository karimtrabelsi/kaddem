package tn.esprit.projet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projet.entities.Departement;
import tn.esprit.projet.entities.Etudiant;
import tn.esprit.projet.repository.DepartementRepository;
import tn.esprit.projet.repository.EtudiantRepository;
import java.util.List;


@Service
public class EtudiantServiceIMPL implements IEtudiantService{
    @Autowired
    EtudiantRepository etudiantRepository;
    DepartementRepository dr;
    @Override
    public List<Etudiant> getAllEtudiant() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant E) {
        return etudiantRepository.save(E);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant E) {
        return etudiantRepository.save(E);
    }

    @Override
    public void deleteEtudiant(long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiantbyid(long id) {

        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId,Integer departementId)
    {
        Etudiant e = etudiantRepository.findById((long)etudiantId).get(); //Parent
        Departement d = dr.findById((long)departementId).get(); //Child
        e.setDepartement(d);
        etudiantRepository.save(e);
    }


}
