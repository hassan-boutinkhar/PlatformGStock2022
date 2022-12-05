package com.hassan.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ligneCommandeClientsDto {


    private Integer id;

    @JsonIgnore
    private ArticleDto articleIdCC;

    @JsonIgnore
    private CommandeClientsDto commandeClientsId;

    }
