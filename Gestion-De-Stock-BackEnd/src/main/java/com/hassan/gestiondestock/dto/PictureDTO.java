package com.hassan.gestiondestock.dto;

import javax.persistence.Column;
import javax.persistence.Lob;

public class PictureDTO {

    private Long id;
    @Lob
    private Byte[]  data;
    private String  nameFile;
}
