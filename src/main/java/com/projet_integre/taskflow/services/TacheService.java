package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.*;
import com.projet_integre.taskflow.repositories.CommentaireRepository;
import com.projet_integre.taskflow.repositories.ProjetRepository;
import com.projet_integre.taskflow.repositories.TacheRepository;
import com.projet_integre.taskflow.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TacheService implements ITacheService{
   @Autowired
    TacheRepository tr;
   @Autowired
   UtilisateurRepository ur;
   @Autowired
   CommentaireRepository cr;
   @Autowired
   ProjetRepository pr;
    @Override
    public Tache creerTache( String titre,Date deadLine, Utilisateur responsable, Projet projet) {
        Tache tache =new Tache(deadLine, titre, responsable, projet );
        responsable.getTaches().add(tache);
        projet.getTaches().add(tache);
        tr.save(tache);
        ur.save(responsable);
        pr.save(projet);

        return tache;
    }

    @Override
    public Tache creerTache( String titre, Date deadLine, Utilisateur responsable, Projet projet, List<Date> breakPoints) {
        Tache tache =new Tache(deadLine, titre, responsable, projet,breakPoints );
        tr.save(tache);
        return  tache;
    }

    @Override
    public void assignerTache(Tache tache, Utilisateur membre) {
        tache.setResponsable(membre);
        membre.getTaches().add(tache);
        tr.save(tache);
        ur.save(membre);
    }

    @Override
    public void supprimerTache(Tache tache) {
        tr.delete(tache);
    }

    @Override
    public void majAvancement(Tache tache, int avancement) {
        tache.setAvancement(avancement);
        tr.save(tache);

    }

    @Override
    public void modifierDeadLine(Tache tache, Date deadLine) {
        tache.setDeadLine(deadLine);
        tr.save(tache);
    }

    @Override
    public void majBreakPoints(Tache tache, List<Date> breakPoints) {
        tache.setBreakPoints(breakPoints);
        tr.save(tache);
    }

    @Override
    public List<Tache> getTachesByProjet(Projet projet) {
        return projet.getTaches();
    }

    @Override
    public List<Tache> getTachesByUser(Utilisateur utilisateur) {

        return tr.findByResponsable(utilisateur);
    }

    @Override
    public void commenterTache(String commentaire, Tache tache) {
        Commentaire com=new Commentaire(commentaire );
        tache.getCommentaires().add(com);
        cr.save(com);
        tr.save(tache);



    }

    @Override
    public void validerTache(Tache tache) {
        tache.setEtat(EtatTache.VALIDEE);
        tr.save(tache);
    }

    @Override
    public Optional<Tache> getTacheById(Integer id) {
        return tr.findById(id);
    }

    @Override
    public void modifierEtat(Tache tache, Integer idEtat) {
        EtatTache newEtat= EtatTache.En_ATTENTE;
        if (idEtat==2) newEtat=EtatTache.EN_COURS;
        if (idEtat==3) newEtat=EtatTache.ATTENTE_VALIDATION;
        if (idEtat==4) newEtat=EtatTache.VALIDEE;
        tache.setEtat(newEtat);
        tr.save(tache);
    }
}
