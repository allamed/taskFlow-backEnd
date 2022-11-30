package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.Utilisateur;

public interface IUtilisateurService {
    public Utilisateur creerUtilisateur(String nom , String email);
    public void supprimerUtilisateur(Utilisateur utilisateur);
}
