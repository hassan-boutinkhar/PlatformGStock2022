package com.hassan.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneVenteDto {

    private Integer id;

    @JsonIgnore
    private ventesDto idVente;

    private BigDecimal quantite;

    private BigDecimal prixUnataire;

    @JsonIgnore
    private ArticleDto articleIdlv;



}
