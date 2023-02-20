package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.Commentaire;
import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Tache;
import com.projet_integre.taskflow.entities.Utilisateur;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITacheService {
    public Tache creerTache(String titre,Date deadLine, Utilisateur responsable, Projet projet);
    //surcharger avec un parametre supplémentaire : breakPoints
    public Tache creerTache(String titre, Date deadLine, Utilisateur responsable, Projet projet, List<Date> breakPoints);
    public void assignerTache(Tache tache, Utilisateur membre);//assigne une tache à un membre
    public void supprimerTache(Tache tache);
    public void majAvancement(Tache tache, int avancement);// cette methode modifie eventuellement l'etat de la tache
    public void modifierDeadLine(Tache tache, Date deadLine);
    public void majBreakPoints(Tache tache, List<Date> breakPoints);
    public List<Tache> getTachesByProjet(Projet projet);
    public List <Tache> getTachesByUser(Utilisateur utilisateur);
    public void commenterTache(Commentaire commentaire, Tache tache); //la date du commentaire sera générée comme date d'aujourd'hui
    public void validerTache(Tache tache);//passe l'etat de la tache de "attente_validation à "validée"
    public Optional<Tache> getTacheById(Integer id);
    public void modifierEtat(Tache tache, Integer id);
    public void modifierTitre(Tache tache, String nouveauTitre);

    public void modifierDescription(Tache tache, String newDesc);
    public Commentaire creerCommentaire(String contenu, Utilisateur auteur );
}
