package com.projet_integre.taskflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @ToString
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    @ManyToOne @JsonIgnore
    private Utilisateur chef;
    @ManyToMany (mappedBy = "projets")
    protected List<Utilisateur> membres=new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date debut;
    @OneToMany(mappedBy = "projet") @JsonIgnore
    private List<Tache> taches=new ArrayList<>();



    public Projet(String nom, Utilisateur chef) {
        this.nom = nom;
        this.chef = chef;
        this.debut=new Date();
    }

    public Projet(String nom, Utilisateur chef, List<Utilisateur> membres) {
        this.nom = nom;
        this.chef = chef;
        this.membres = membres;
        this.debut=new Date();
    }

}
