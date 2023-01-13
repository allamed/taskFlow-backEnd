package com.projet_integre.taskflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @ToString @DynamicUpdate
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    @ManyToOne
    private Utilisateur chef;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "projet_membres",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "membre_id")

    )
    protected List<Utilisateur> membres=new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date debut;
   // @OneToMany(mappedBy = "projet") @JsonIgnore
    //private List<Tache> taches=new ArrayList<>();



    public Projet(String nom, Utilisateur chef) {
        this.nom = nom;
        this.chef = chef;
        this.debut=new Date();
    }



}
