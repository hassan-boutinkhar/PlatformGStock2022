package com.hassan.gestiondestock.dto;

import com.hassan.gestiondestock.model.adress;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class adressDto {

    private String  adress1;

    private String  adress2;

    private String  ville;

    private String  codePostale;

    private String  pays;
}
