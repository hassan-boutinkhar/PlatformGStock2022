package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Informations {

    @Column(name = "nom")
    private String  nom;

    @Column(name = "prenom")
    private String  prenom;


    @Column(name = "mail")
    private String  mail;

    @Column(name = "numTel")
    private String  numTel;
}
