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
public class CommandeClientsDto {

    private Integer id;

    private String  code;

    private Instant dateCommande;


   /* @JsonIgnore
    private clientsDto commandeClients;

    private List<ligneCommandeClientsDto> ligneCommandeClients;*/


}
