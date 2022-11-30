package com.projet_integre.taskflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
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
}
