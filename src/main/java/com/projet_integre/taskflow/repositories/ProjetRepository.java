package com.projet_integre.taskflow.repositories;

import com.projet_integre.taskflow.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    //@Modifying
    //@Query("update EARAttachment ear set ear.status = ?1 where ear.id = ?2")
   // int setStatusForEARAttachment(Integer status, Long id);
}
