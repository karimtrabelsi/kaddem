package tn.esprit.projet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "Contrat")
@Getter
@Setter
public class Contrat implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idContrat")
    private Long idContrat;
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date DateDebutContrat;

    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date DateFinContrat;

    private  boolean archive;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private float montant;
    @ManyToOne(cascade = CascadeType.ALL)
    Etudiant Etudiant;

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

    public Date getDateDebutContrat() {
        return DateDebutContrat;
    }

    public void setDateDebutContrat(Date dateDebutContrat) {
        DateDebutContrat = dateDebutContrat;
    }

    public Date getDateFinContrat() {
        return DateFinContrat;
    }

    public void setDateFinContrat(Date dateFinContrat) {
        DateFinContrat = dateFinContrat;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
