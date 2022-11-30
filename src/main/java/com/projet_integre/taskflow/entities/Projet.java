package com.projet_integre.taskflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Utilisateur chef;
    @ManyToMany (mappedBy = "projets")
    protected List<Utilisateur> membres=new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date debut;
    @OneToMany(mappedBy = "projet")
    private List<Tache> taches;

    @PrePersist
    private void onCreate() {
        debut = new Date();
    }


}
