package tn.esprit.projet.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cours")
@Getter
@Setter
public class Cours implements Serializable {


    @ManyToOne(cascade = CascadeType.ALL)
    Formation Formation;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCours")
    private Long idCours;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;


}
