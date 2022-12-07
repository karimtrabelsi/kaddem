package tn.esprit.projet.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table( name = "Reclamation")
@Getter
@Setter
public class Reclamation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idReclamation")
    private Long idReclamation;

    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date DateReclamation = new Date(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    private TypeRec Type;

    private String screenshot;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    Etudiant Etudiant;

    public void setIdReclamation(Long idReclamation) {
        this.idReclamation = idReclamation;
    }



    public void setDateReclamation(Date dateReclamation) {
        DateReclamation = dateReclamation;
    }

    public void setType(TypeRec type) {
        Type = type;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public Long getIdReclamation() {
        return idReclamation;
    }

    public Date getDateReclamation() {
        return DateReclamation;
    }

    public TypeRec getType() {
        return Type;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
