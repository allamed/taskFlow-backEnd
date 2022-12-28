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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ProjetController {
    @Autowired
    ProjetService ps;
    @Autowired
    UtilisateurService us;
    @GetMapping(path = "/projets/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity taches(@PathVariable String email){
        Utilisateur utilisateur=us.getUtilisateurByEmail(email);
        List<Projet> projets= ps.getProjetByChef(utilisateur);
        HashMap<String, List<Projet>> response = new HashMap<>();
        response.put("projets", projets);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
