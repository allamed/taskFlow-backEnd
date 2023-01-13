package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Tache;
import com.projet_integre.taskflow.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface IProjetService {
    public Projet creerProjet(String nom, Utilisateur chef);

    public void supprimerProjet(Projet projet);
    public List<Projet> getProjetByChef(Utilisateur chef);
    public Optional<Projet> getProjetById(Integer id);


}
