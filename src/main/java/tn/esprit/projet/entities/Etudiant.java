package tn.esprit.projet.entities;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "Etudiant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode

public class Etudiant implements Serializable {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idEtudiant")
    private Long idEtudiant; // Clé primaire

    @NonNull
    private String prenom;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Domaine domaine;
    @ManyToOne(cascade = CascadeType.ALL)
    Departement Departement;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "Etudiant")
    private Set<Contrat> Contrats;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set <Equipe> Equipes;





}
