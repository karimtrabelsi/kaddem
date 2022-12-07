package tn.esprit.projet.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String email;
    @Enumerated(EnumType.STRING)
    private Domaine domaine;
    @ManyToOne(cascade = CascadeType.ALL)
    Departement Departement;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "etudiant")
    @JsonIgnoreProperties({"etudiant"})
    private Set<Contrat> contrats;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set <Equipe> Equipes;

}
