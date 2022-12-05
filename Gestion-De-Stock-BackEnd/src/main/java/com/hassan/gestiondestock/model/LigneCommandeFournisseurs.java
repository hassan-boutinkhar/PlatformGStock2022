package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "LigneCommandeFournisseurs")
public class LigneCommandeFournisseurs extends AbsractEntity{

    @Column(name = "articleId")
    private String articleIdCC;

    @Column(name = "quantite")
    private Long quantite;

    @Column(name = "prixTotale")
    private Long prixTotale;





}
