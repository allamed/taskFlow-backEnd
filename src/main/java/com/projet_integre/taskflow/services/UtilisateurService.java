package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.repositories.ProjetRepository;
import com.projet_integre.taskflow.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurService implements IUtilisateurService{
    @Autowired
    UtilisateurRepository ur;
    @Autowired
    ProjetRepository pr;

    @Override
    public List<Utilisateur> getAll() {
        return ur.findAll();
    }

    @Override
    public Utilisateur getUtilisateurById(Integer id) {
        Optional<Utilisateur> user = ur.findById(id);
        return user.get();

    }

    @Override
    public Utilisateur creerUtilisateur(String nom, String email) {
        Utilisateur utilisateur=new Utilisateur(nom, email);
       ur.save(utilisateur);
        return utilisateur;
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        ur.delete(utilisateur);
    }
    @Override
    public void addMemberToProject( Utilisateur membre,Projet projet) {
        membre.integrerProjet(projet);
        ur.save(membre);
        pr.save(projet);

    }

    @Override
    public void removeMembreFromProject(Utilisateur membre, Projet projet) {
        membre.quitterProjet(projet);
        ur.save(membre);
        pr.save(projet);
    }

    @Override
    public List<Projet> getProjetByMembre(Utilisateur membre) {
        return membre.getProjets();
    }
}
