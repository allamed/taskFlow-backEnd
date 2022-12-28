package com.projet_integre.taskflow.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private String nom;
    @Id
    private String email;
    private String password;
}
