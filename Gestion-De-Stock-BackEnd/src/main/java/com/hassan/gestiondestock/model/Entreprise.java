package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Entreprise")
public class Entreprise extends AbsractEntity{

    @Column(name = "nom")
    private String  nom;

    @Column(name = "description")
    private String  description;

    @Embedded
    private adress adress;


    @Column(name = "codeFiscale")
    private String  codeFiscale;

    @Column(name = "photo")
    private String  photo;

    @Column(name = "email")
    private String  email;

    @Column(name = "numTel")
    private String  numTel;

    @Column(name = "siteWeb")
    private String  siteWeb;

   /* @OneToMany(mappedBy = "idEntrepriseU")
    private List<Utilisateurs> utilisateurs;

    @OneToMany(mappedBy = "idEntreprise")
    private List<Article> articles;

    @OneToMany(mappedBy = "idEntrepriseV")
    private List<Ventes> ventes;

    @ManyToMany(mappedBy = "idEntrepriseC")
    private List<clients> clientsList;

    @ManyToMany(mappedBy = "idEntrepriseF")
    private List<Fournisseur> fournisseurList;*/

}
