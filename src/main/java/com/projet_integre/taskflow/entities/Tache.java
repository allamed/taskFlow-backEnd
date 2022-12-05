package com.projet_integre.taskflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor  @ToString
public class Tache {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date debut;
    @Temporal(TemporalType.DATE)
    private Date deadLine;

    private String titre;
    private Integer avancement;
    @Temporal(TemporalType.DATE)
    private List<Date> breakPoints=new ArrayList<Date>();
    @ManyToOne
    private Utilisateur responsable;
    @ManyToOne
    private Projet projet;
    @OneToMany
    private List<Commentaire> commentaires;
    @Enumerated(EnumType.STRING)
    private EtatTache etat;

    public Tache(Date deadLine, String titre, Utilisateur responsable, Projet projet) {
        this.deadLine = deadLine;
        this.titre = titre;
        this.responsable = responsable;
        this.projet = projet;
        this.debut=new Date();
        this.avancement=0;
        this.breakPoints=new ArrayList<>();
        this.commentaires=new ArrayList<>();
        this.etat=EtatTache.En_ATTENTE;

    }
    public Tache(Date deadLine, String titre, Utilisateur responsable, Projet projet,List<Date> breakPoints) {
        this.deadLine = deadLine;
        this.titre = titre;
        this.responsable = responsable;
        this.projet = projet;
        this.debut=new Date();
        this.avancement=0;
        this.breakPoints=breakPoints;
        this.commentaires=new ArrayList<>();
        this.etat=EtatTache.En_ATTENTE;

    }

}
