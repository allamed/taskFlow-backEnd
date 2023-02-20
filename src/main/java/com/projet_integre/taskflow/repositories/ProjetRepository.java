package com.projet_integre.taskflow.repositories;

import com.projet_integre.taskflow.entities.Projet;
import com.projet_integre.taskflow.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "projets", path = "projets")
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    public List<Projet> findProjetByChef_Id (Integer chefId);
    @Query("SELECT p FROM Projet p JOIN p.membres m WHERE m.id = :utilisateurId")
    List<Projet> findProjetByMemberId(@Param("utilisateurId") Integer utilisateurId);






}
