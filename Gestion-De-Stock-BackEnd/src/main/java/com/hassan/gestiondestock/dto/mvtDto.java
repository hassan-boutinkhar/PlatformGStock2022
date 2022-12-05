package com.hassan.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hassan.gestiondestock.model.MvtDeStocks;
import com.hassan.gestiondestock.model.TypeMvt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class mvtDto {

    private Long id;

    private String dateMvt;

    private BigDecimal quantite;

    private Long articleId;

    private TypeMvt typeMvt;

    private String idEntreprise;

    private BigDecimal  quantiteT;



}
