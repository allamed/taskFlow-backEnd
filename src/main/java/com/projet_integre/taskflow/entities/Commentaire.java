package com.projet_integre.taskflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Entity @Data  @NoArgsConstructor @ToString
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contenu;
    @ManyToOne
    private Utilisateur auteur;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Commentaire(String contenu, Utilisateur auteur) {
        this.contenu = contenu;
        this.auteur=auteur;
        this.date=new Date();
    }
}
