package com.projet_integre.taskflow.repositories;

import com.projet_integre.taskflow.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
}
