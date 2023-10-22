package com.example.annonce_microservice.Entities;

import com.example.annonce_microservice.Enum.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceCollaboration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long iduser;
    private String description;
    private double prix;
    private int nbplace;
    private int reserve;

    @Enumerated(EnumType.STRING)
    private Type type;
    private Date dateLimite;


}