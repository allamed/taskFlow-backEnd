package com.projet_integre.taskflow.services;

import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.repositories.ProjetRepository;
import com.projet_integre.taskflow.repositories.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class ProjetService implements IProjetService{
    @Autowired
    ProjetRepository pr;
    @Autowired
    UtilisateurRepository ur;
    @Override
    public Projet creerProjet(String nom, Utilisateur chef) {
        Projet projet=new Projet(nom, chef);
        chef.getProjetsDiriges().add(projet);
        ur.save(chef);
        pr.save(projet);
        return projet;
    }

    @Override
    public Projet creerProjet(String nom, Utilisateur chef, List<Utilisateur> membres) {
        Projet projet=new Projet(nom, chef, membres);
        pr.save(projet);
        return projet;    }

    @Override
    public void supprimerProjet(Projet projet) {
        pr.delete(projet);
    }

    @Override
    public List<Projet> getProjetByChef(Utilisateur chef) {
        int chefId=chef.getId();

        return pr.findProjetByChef_Id(chefId);

    }


}
