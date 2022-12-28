package com.projet_integre.taskflow.controllers;

import com.projet_integre.taskflow.entities.Tache;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.services.TacheService;
import com.projet_integre.taskflow.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TacheController {
    @Autowired
    TacheService ts;
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
}
