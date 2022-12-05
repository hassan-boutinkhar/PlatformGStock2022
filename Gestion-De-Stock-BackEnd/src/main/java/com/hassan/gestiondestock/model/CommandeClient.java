package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CommandeClient")
public class CommandeClient extends AbsractEntity{

    @Column(name = "code")
    private String  code;

    @Column(name = "dateCommande")
    private String dateCommande;

    @Column(name = "idClient")
    private String idClient;

    @Column(name = "etatCommande")
    private String etatCommande;

    @Column(name = "idEntreprise")
    private String  idEntreprise;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "idCommande")
    private List<ligneCommandeClients> ligneCommandeClients;

    @Column(name = "prixTotale")
    private Long  prixTotale;


}
