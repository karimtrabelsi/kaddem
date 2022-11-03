package tn.esprit.projet.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "DetailEquipe")


@Getter
@Setter
public class DetailEquipe  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdEquipe")
    private Long IdEquipe;
    private Long salle;
    private String thematique;
    @OneToOne(mappedBy = "DetailEquipe1")
    private  Equipe Equipe;


}
