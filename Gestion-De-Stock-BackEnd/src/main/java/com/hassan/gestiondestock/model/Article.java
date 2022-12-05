package com.hassan.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbsractEntity{

    @Column(name = "codeArticle")
    private String  codeArticle;

    @Column(name = "designation")
    private String  designation;

    @Column(name = "prixUnitaireHt")
    private BigDecimal prixUnitaireHt;

    @Column(name = "prixUnitaireTtc")
    private BigDecimal  prixUnitaireTtc;

    @Column(name = "touxTva")
    private BigDecimal  touxTva;

    @OneToOne(fetch =FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "articleImageId",referencedColumnName = "id")//KKKK
    private ImageModule  articleImage;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private category categoryId;

/*    @OneToMany(mappedBy = "articleId")
    private List<MvtDeStocks> mvtDeStocks;*/

    @Column(name = "idEntreprise")
    private String  idEntreprise;

    @Column(name = "quantite")
    private BigDecimal  quantite;

/*    @OneToMany(mappedBy = "articleIdlv")
    private List<LigneVente> ligneVentes;

    @OneToMany(mappedBy = "articleIdCF")
    private List<LigneCommandeFournisseurs> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "articleIdCC")
    private List<ligneCommandeClients> ligneCommandeClientsList;*/

}
