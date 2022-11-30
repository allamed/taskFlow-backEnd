package com.projet_integre.taskflow.repositories;

import com.projet_integre.taskflow.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer> {
}
