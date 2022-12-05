package com.hassan.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ventesDto {

    private Integer id;

    private String code;

    private Instant dateVente;

    private String commantaire;

    @JsonIgnore
    private EntrepriseDto idEntrepriseV;

    private List<LigneVenteDto> ligneVente;




}
