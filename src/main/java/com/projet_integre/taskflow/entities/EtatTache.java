package com.projet_integre.taskflow.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum EtatTache {
    En_ATTENTE, EN_COURS, ATTENTE_VALIDATION,VALIDEE

}
