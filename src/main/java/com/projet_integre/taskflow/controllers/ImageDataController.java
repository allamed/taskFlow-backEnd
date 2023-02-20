package com.projet_integre.taskflow.controllers;

import com.projet_integre.taskflow.entities.ImageData;
import com.projet_integre.taskflow.entities.Utilisateur;
import com.projet_integre.taskflow.services.ImageDataService;
import com.projet_integre.taskflow.services.UtilisateurService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;
    @Autowired
    private UtilisateurService us;

    @PostMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("id") Integer id ) throws IOException {

        Utilisateur user= us.getUtilisateurById(id);

      String response= imageDataService.uploadImage(file,user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/info/{id}")
    @CrossOrigin(origins = "*")
    public void getImageInfoByUserId(@PathVariable("id") Integer id, HttpServletResponse response){
        Utilisateur user = us.getUtilisateurById(id);
        byte[] image = imageDataService.getImage(user);

        response.setContentType("image/jpeg");
        response.setHeader("Content-Length", String.valueOf(image.length));

        try {
            response.getOutputStream().write(image);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@GetMapping("/{name}")
    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name){
        byte[] image = imageDataService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }*/


}
