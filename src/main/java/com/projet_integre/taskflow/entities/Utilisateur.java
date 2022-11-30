package com.projet_integre.taskflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private String email;

    @ManyToMany
    private List<Projet> projets = new ArrayList<>();
    @OneToMany(mappedBy = "chef")
    private List<Projet> projetsDiriges=new ArrayList<>();
    @OneToMany(mappedBy = "responsable")
    private List<Tache> taches=new ArrayList<>();




    
    

}
