package com.projet_integre.taskflow.controllers;

import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurService us;
    @GetMapping(path = "/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Utilisateur> utilisateurs(){
        return us.getAll();
    }
    @GetMapping(path = "/utilisateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur utilisateurs(@PathVariable Integer id){
        return us.getUtilisateurById(id);
    }
    @PostMapping(path = "/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewUser(@RequestBody Utilisateur utilisateur) {
        us.creerUtilisateur(utilisateur.getNom(), utilisateur.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
