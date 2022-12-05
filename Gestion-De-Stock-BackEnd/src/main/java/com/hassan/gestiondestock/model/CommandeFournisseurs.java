package com.hassan.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandeFournisseurs")
public class CommandeFournisseurs extends AbsractEntity{


    @Column(name = "code")
    private String  code;

    @Column(name = "dateCommande")
    private String dateCommande;

    @Column(name = "fournisseurid")
    private String idClient;

    @Column(name = "etatCommande")
    private String etatCommande;

    @Column(name = "idEntreprise")
    private String  idEntreprise;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "idCommande")
    private List<LigneCommandeFournisseurs> ligneCommandeClients;

    @Column(name = "prixTotale")
    private Long  prixTotale;
}
