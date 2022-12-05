package com.hassan.gestiondestock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbsractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

    //@CreatedDate DOIT GENERER AUTOMATIQUEMENT PAR HIBERNATE
    @CreatedDate
    @Column(name="creationDate" ,nullable = false,updatable = false)// UPDATABLE ON NE PAS METS A JOUR NOTRE COLONNE DIT LA CREATION
    @JsonIgnore
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastDateModified")
    @JsonIgnore
    private Instant modifiedDate;

/*    @PrePersist// AVANT DE CHAQUE SAUVGAREDER IL VA MODFIER LA VALEUR DE CREATIONDATE
    void prePersit(){
        creationDate= Instant.now();
    }
    @PreUpdate// AVANT DE DE CHAQUE MISE A JOUR IL VA MODFIER LA VALEUR DE MODIFIEDDATE
    void preUpdate(){
        modifiedDate=Instant.now();
        //ON PREPARE STATEMENT NOUS PERMEAT DE PREPARER LE REQUET AVANT DE LENVOYER AU BASE DE DONNES
    }*/

}
