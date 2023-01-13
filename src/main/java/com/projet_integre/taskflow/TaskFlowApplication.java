package com.projet_integre.taskflow;

import com.projet_integre.taskflow.entities.*;
import com.projet_integre.taskflow.repositories.ProjetRepository;
import com.projet_integre.taskflow.repositories.TacheRepository;
import com.projet_integre.taskflow.repositories.UtilisateurRepository;
import com.projet_integre.taskflow.services.IProjetService;
import com.projet_integre.taskflow.services.ITacheService;
import com.projet_integre.taskflow.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TaskFlowApplication  {

    @Autowired
    IUtilisateurService us;

    @Autowired
    ITacheService ts;

    @Autowired
    IProjetService ps;
    public static void main(String[] args) {
        SpringApplication.run(TaskFlowApplication.class, args);
    }

    @Bean
    CommandLineRunner start(){

        return args -> {
           Utilisateur u1= us.creerUtilisateur("allam","allam@gmail.com");
           Projet p1= ps.creerProjet("projet1",u1 );
            Projet p2= ps.creerProjet("projet2",u1 );
            Projet p3= ps.creerProjet("projet3",u1 );

           Utilisateur u2= us.creerUtilisateur("aouad","aouad@gmail.com");
            Utilisateur u3= us.creerUtilisateur("imami","imami@gmail.com");
           us.addMemberToProject(u2,p1);
            us.addMemberToProject(u2,p3);
            us.addMemberToProject(u3,p3);
            us.addMemberToProject(u3,p1);
           //List<Projet> projets= ps.getProjetByChef(u1);
          // projets.forEach(x->System.out.println(x.getNom()));
            Tache t1 =ts.creerTache("tache1",new Date(),u1,p1);
            Tache t2 =ts.creerTache("tache2",new Date(),u2,p2);
            Tache t3 =ts.creerTache("tache3",new Date(),u1,p1);
            ts.majAvancement(t1,25);
            ts.majAvancement(t2,37);
            ts.majAvancement(t3,75);

           List<Tache> taches= ts.getTachesByUser(u1);
            System.out.println("********************");
           taches.forEach(x-> System.out.println(x.getTitre()));
           List <Utilisateur> users = us.getAll();
           users.forEach(x-> System.out.println(x.getNom()));

           //us.removeMembreFromProject(u2,p1);



            /*Projet p1= new Projet(null, null, new ArrayList<>(), new Date(),new ArrayList<>() );
            Projet p2= new Projet(null, null, new ArrayList<>(), new Date(),new ArrayList<>() );
            pr.save(p1);pr.save(p2);


            List<Projet> projets=new ArrayList<>(); projets.add(p1);projets.add(p2);
            Utilisateur u1= new Utilisateur(null, "allam", "allam@gmail.com", projets,new ArrayList<>(),new ArrayList<>() );
            Utilisateur u2=new Utilisateur(null, "aouad", "aouad@gmail.com", new ArrayList<>(),new ArrayList<>(),new ArrayList<>() );
            Utilisateur u3=new Utilisateur(null, "imami", "imami@gmail.com", new ArrayList<>(),new ArrayList<>(),new ArrayList<>() );
            Utilisateur u4=new Utilisateur(null, "douiba", "douiba@gmail.com", new ArrayList<>(),new ArrayList<>(),new ArrayList<>() );
            ur.save(u1);ur.save(u2);ur.save(u3);ur.save(u4);

            List<Utilisateur> membres= new ArrayList<>();
            membres.add(u1);membres.add(u2);membres.add(u3);membres.add(u4);

            Tache t1 = new Tache(null, new Date(), new Date(), "tache1", 0 , new ArrayList<>(), u2, p1, new ArrayList<>(), EtatTache.En_ATTENTE);
            Tache t2 = new Tache(null, new Date(), new Date(), "tache2", 0 , new ArrayList<>(), u3, p2, new ArrayList<>(), EtatTache.En_ATTENTE);
            tr.save(t1);tr.save(t2);*/
        };
    }
}
