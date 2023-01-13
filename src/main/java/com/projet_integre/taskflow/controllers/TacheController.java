package com.projet_integre.taskflow.controllers;

import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Tache;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.services.ProjetService;
import com.projet_integre.taskflow.services.TacheService;
import com.projet_integre.taskflow.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class TacheController {
    @Autowired
    TacheService ts;
    @Autowired
    ProjetService ps;
    @Autowired
    UtilisateurService us;
    @GetMapping(path = "/taches/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity taches(@PathVariable String email){
        Utilisateur utilisateur=us.getUtilisateurByEmail(email);
      List<Tache> taches= ts.getTachesByUser(utilisateur);
        HashMap<String, List<Tache>> response = new HashMap<>();
        response.put("taches", taches);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/taches/modifierEtat", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity majEtatTache(@RequestBody Map<String, String> info ){
        Tache tache=ts.getTacheById(Integer.parseInt(info.get("idTache"))).get();
        ts.modifierEtat(tache, Integer.parseInt(info.get("nouveauEtat")));


        HashMap<String, Tache> response = new HashMap<>();
       response.put("tache", tache);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping(path = "/taches/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity creerTache(@RequestBody Map<String, String>  newTask ) throws ParseException {
        System.out.println(newTask);

        Utilisateur responsable = us.getUtilisateurById(Integer.parseInt(newTask.get("responsableId")) );
        Projet projet = ps.getProjetById(Integer.parseInt(newTask.get("projetId"))).get();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = df.parse(newTask.get("deadLine"));
        Tache tache= ts.creerTache(newTask.get("titre"), date, responsable,projet );




        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
