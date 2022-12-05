package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "LigneVente")
public class LigneVente extends AbsractEntity{

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Ventes idVente;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixUnataire")
    private BigDecimal prixUnataire;

    @ManyToOne
    @JoinColumn(name = "articleIdlv")
    private Article articleIdlv;
}
