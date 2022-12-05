package com.hassan.gestiondestock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDto {

    private Integer id;

    private String  nom;

    private String  description;

    private adressDto adress;

    private String  codeFiscale;

    private String  photo;

    private String  email;

    private String  numTel;

    private String  siteWeb;

/*    @JsonIgnore
    private List<utilisateursDto> utilisateurs;*/

    @JsonIgnore
    private List<ArticleDto> articles;

    @JsonIgnore
    private List<ventesDto> ventes;

/*    @JsonIgnore
    private List<clientsDto> clientsList;

    @JsonIgnore
    private List<fournisseurDto> fournisseurList;*/

}
