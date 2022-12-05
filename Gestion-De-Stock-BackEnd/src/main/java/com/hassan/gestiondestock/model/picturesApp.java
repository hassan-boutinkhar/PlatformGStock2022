package com.hassan.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "picturesApp")
public class picturesApp extends AbsractEntity{

    @Lob
    @Column(name = "photo")
    private Byte[] photo;

    @Column(name = "nomPhoto")
    private String  nomPhoto;


}
