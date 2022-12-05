package com.hassan.gestiondestock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hassan.gestiondestock.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureAppDTO {

    private Long id;

    @Lob
    private byte[] photo;

    private String  nomPhoto;


}
