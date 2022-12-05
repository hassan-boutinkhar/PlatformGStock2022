package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MvtDeStocks")
public class MvtDeStocks extends AbsractEntity{

    @Column(name = "dateMvt")
    private String dateMvt;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "articleId")
    private Long articleId;

    @Column(name = "typeMvt")
    private TypeMvt typeMvt;

    @Column(name = "IdEntreprise")
    private String idEntreprise;

    @Column(name = "quantiteT")
    private BigDecimal  quantiteT;
}
