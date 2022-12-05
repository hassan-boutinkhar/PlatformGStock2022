package com.hassan.gestiondestock.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Ventes")
public class Ventes extends AbsractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "dateVente")
    private Instant dateVente;

    @Column(name = "commantaire")
    private String commantaire;

    @OneToMany(mappedBy = "idVente")
    private List<LigneVente> ligneVente;

    @ManyToOne
    @JoinColumn(name = "idEntrepriseV")
    private Entreprise idEntrepriseV;
}
