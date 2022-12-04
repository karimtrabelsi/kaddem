package tn.esprit.projet.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "Equipe")
@Getter
@Setter
public class Equipe implements  Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idEquipe")
    private Long idEquipe; // Clé primaire
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    @OneToOne(cascade = CascadeType.ALL)
    private DetailEquipe DetailEquipe1;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "Equipes")
    private Set<Etudiant> Etudiants;




}
