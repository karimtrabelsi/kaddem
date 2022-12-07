package tn.esprit.projet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
    private String nom;
    @Temporal (TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date dateformation;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Formateur Formateur;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private float Prix;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> Etudiants;
    private Boolean archive;


}
