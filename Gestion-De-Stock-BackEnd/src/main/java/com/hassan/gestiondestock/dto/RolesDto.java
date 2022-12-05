package com.hassan.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hassan.gestiondestock.model.Roles;
import com.hassan.gestiondestock.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {

    private Integer id;

    private String roleName;
/*
    @JsonIgnore
    private utilisateursDto utilisateurId;*/

    }
