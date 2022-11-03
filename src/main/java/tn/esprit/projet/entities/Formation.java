package tn.esprit.projet.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Formation")
@Getter
@Setter
public class Formation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFormation")
    private Long idFormation;
    private String Nom;
    @Temporal(TemporalType.DATE)
    private Date DateFormation;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Formateur Formateur;
    @Enumerated(EnumType.STRING)
    private Specialite Specialite;
    private float Prix;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> Etudiants;


}
