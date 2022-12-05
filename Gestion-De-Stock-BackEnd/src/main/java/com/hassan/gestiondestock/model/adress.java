package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class adress {

    @Column(name = "adress1")
    private String  adress1;

    @Column(name = "adress2")
    private String  adress2;

    @Column(name = "ville")
    private String  ville;

    @Column(name = "codePostale")
    private String  codePostale;

    @Column(name = "pays")
    private String  pays;

}
