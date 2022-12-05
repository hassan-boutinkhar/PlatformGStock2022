package com.hassan.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Lob;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Integer id;

    private String  codeArticle;

    private String  designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal  prixUnitaireTtc;

    private BigDecimal  touxTva;

    private byte[] photo;

    private String  nomPhoto;

    private CategoryDTO categoryId;

    @JsonIgnore
    private List<mvtDto> mvtDeStocks;

    private String idEntreprise;


    @JsonIgnore
    private List<LigneVenteDto> ligneVentes;

    @JsonIgnore
    private List<ligneCommandeFournisseurDto> ligneCommandeFournisseurs;

    @JsonIgnore
    private List<ligneCommandeClientsDto> ligneCommandeClientsList;

}
