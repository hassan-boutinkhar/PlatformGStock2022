package com.hassan.gestiondestock.dto;

import com.hassan.gestiondestock.model.Informations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationDto {

    private String  nom;

    private String  prenom;

    private String  photo;

    private String  mail;

    private String  numTel;


}
