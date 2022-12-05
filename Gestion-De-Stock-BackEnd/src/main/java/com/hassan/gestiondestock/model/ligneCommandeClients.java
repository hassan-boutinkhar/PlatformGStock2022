package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ligneCommandeClients")
public class ligneCommandeClients extends AbsractEntity{


    @Column(name = "articleId")
    private String articleIdCC;

    @Column(name = "quantite")
    private Long quantite;

    @Column(name = "prixTotale")
    private Long prixTotale;

}
