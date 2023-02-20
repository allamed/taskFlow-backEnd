package com.projet_integre.taskflow.repositories;

import com.projet_integre.taskflow.entities.ImageData;
import com.projet_integre.taskflow.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByUtilisateur(Utilisateur utilisateur);

}
