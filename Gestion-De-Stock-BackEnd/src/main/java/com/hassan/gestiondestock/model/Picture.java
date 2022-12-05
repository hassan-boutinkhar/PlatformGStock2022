package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Picture")
public class Picture extends AbsractEntity{
    @Lob
    @Column(name = "Byte")
    private Byte[]  data;
    @Column(name = "NomPicture")
    private String  nameFile;
}
