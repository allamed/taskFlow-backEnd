package com.projet_integre.taskflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Utilisateur {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private String email;

    @ManyToMany
    @JsonIgnore
    private List<Projet> projets = new ArrayList<>();
    @OneToMany(mappedBy = "chef")
    @JsonIgnore
    private List<Projet> projetsDiriges=new ArrayList<>();
    @OneToMany(mappedBy = "responsable")
    @JsonIgnore
    private List<Tache> taches=new ArrayList<>();
    public Utilisateur(String nom, String email){
        this.nom=nom;
        this.email=email;
    }
    public void integrerProjet(Projet projet){
        this.projets.add(projet);
        projet.getMembres().add(this);
    }
    public void quitterProjet(Projet projet){
        this.projets.remove(projet) ;
        projet.getMembres().remove(this);
    }




    
    

}
