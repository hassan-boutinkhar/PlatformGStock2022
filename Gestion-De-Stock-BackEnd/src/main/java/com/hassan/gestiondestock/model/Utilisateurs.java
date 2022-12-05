package com.hassan.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Utilisateurs")
public class Utilisateurs extends AbsractEntity{

    @Column(name = "nom")
    private String  nom;

    @Column(name = "prenom")
    private String  prenom;


    @Column(name = "mail")
    private String  mail;

    @Column(name = "numTel")
    private String  numTel;

    @Column(name = "adress1")
    private String  adress1;

    @Column(name = "adress2")
    private String  adress2;

    @Column(name = "ville")
    private String  ville;

    @Column(name = "codePostale")
    private String  codePostale;

    @Column(name = "pays")
    private String  pays;

    @Column(name = "motDePass")
    private String motDepass;

    @Column(name = "dateDeNaissance")
    private String dateDeNaissance;

    @OneToOne(fetch =FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "articleImageId",referencedColumnName = "id")
    private ImageModule  userImageId;

    @Column(name = "idEntreprise")
    private String  idEntreprise;

    @Column(name = "roles")
    private String  roles;

}
