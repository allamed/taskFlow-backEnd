package com.projet_integre.taskflow.controllers;

import com.projet_integre.taskflow.entities.LoginUser;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.repositories.UtilisateurRepository;
import com.projet_integre.taskflow.services.UtilisateurService;
import jakarta.servlet.http.HttpServletResponse;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurService us;

    @GetMapping(path = "/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Utilisateur> utilisateurs(){
        return us.getAll();
    }
    @GetMapping(path = "/utilisateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur utilisateur(@PathVariable Integer id){
        return us.getUtilisateurById(id);
    }
    @PostMapping(path = "/utilisateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity createNewUser(@RequestBody Utilisateur utilisateur) {
        String email=utilisateur.getEmail();
        //String err="Email déjà inscrit";
        HashMap<String, String> error = new HashMap<>();
        error.put("error", "email deja inscrit");



        if (us.getUtilisateurByEmail(email)!= null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error );
        }
       Utilisateur user= us.creerUtilisateur(utilisateur.getNom(), utilisateur.getEmail());
       return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity login(@RequestBody LoginUser user) {
        Utilisateur utilisateur=us.getUtilisateurByEmail(user.getEmail());
        HashMap<String, String> NotFound = new HashMap<>();
        NotFound.put("error", "pas d'utilisateur inscrit avec cet email");
        HashMap<String, String> incorrectPassword= new HashMap<>();
        incorrectPassword.put("error", "incorrectPassword");

        if (utilisateur== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(NotFound );
        }
        if (!Objects.equals(user.getPassword(), "123")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectPassword );
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utilisateur);

    }
    @RequestMapping(path="/updateUser", method = RequestMethod.PATCH,produces = MediaType.APPLICATION_JSON_VALUE )
    @CrossOrigin(origins = "*")
    public ResponseEntity modifierUtilisateur(@RequestBody Utilisateur user) {
        Utilisateur utilisateur=us.getUtilisateurByEmail(user.getEmail());
        HashMap<String, String> NotFound = new HashMap<>();
        NotFound.put("error", "utilisateur non trouvé");
        if (utilisateur== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(NotFound );
        }
        utilisateur.setNom(user.getNom());
        us.saveUser(utilisateur);

        return ResponseEntity.status(HttpStatus.OK).body(utilisateur);

    }
    @DeleteMapping(path = "/utilisateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable Integer id) {
        Utilisateur utilisateur= us.getUtilisateurById(id);
        us.supprimerUtilisateur(utilisateur);
    }
}
