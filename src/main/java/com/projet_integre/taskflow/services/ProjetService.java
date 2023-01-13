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
public class ProjetService implements IProjetService{
    @Autowired
    ProjetRepository pr;
    @Autowired
    UtilisateurRepository ur;
    @Override
    public Projet creerProjet(String nom, Utilisateur chef) {
        Projet projet=new Projet(nom, chef);
        //chef.getProjetsDiriges().add(projet);
        //ur.save(chef);
        pr.save(projet);
        return projet;
    }



    @Override
    public void supprimerProjet(Projet projet) {
        pr.delete(projet);
        //to do limame, operation inverse pour chaque membre supprimer le projet de la liste des projets , et pour le chef supprimer le projet de la liste des projets diriges
    }

    @Override
    public List<Projet> getProjetByChef(Utilisateur chef) {
        int chefId=chef.getId();

        return pr.findProjetByChef_Id(chefId);

    }

    @Override
    public Optional<Projet> getProjetById(Integer id) {
        return pr.findById(id);
    }


}
