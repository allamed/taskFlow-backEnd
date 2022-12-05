package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    public List<Utilisateur> getAll();
    public Utilisateur getUtilisateurById(Integer id);
    public Utilisateur creerUtilisateur(String nom , String email);
    public void supprimerUtilisateur(Utilisateur utilisateur);
    public void addMemberToProject(Utilisateur membre, Projet projet);
    public void removeMembreFromProject(Utilisateur membre, Projet projet);
    public List<Projet> getProjetByMembre (Utilisateur membre);

}
