package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.LoginUser;
import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurService {
    public List<Utilisateur> getAll();
    public Utilisateur getUtilisateurById(Integer id);
    public Utilisateur getUtilisateurByEmail(String email);
    public Utilisateur creerUtilisateur(String nom , String email);
    public void supprimerUtilisateur(Utilisateur utilisateur);
    public void addMemberToProject(Utilisateur membre, Projet projet);
    public void removeMembreFromProject(Utilisateur membre, Projet projet);
    public List<Projet> getProjetByMembre (Utilisateur membre);
    public Optional<LoginUser> getLoginUserByEmail(String email);
    public void saveUser(Utilisateur utilisateur);

}
