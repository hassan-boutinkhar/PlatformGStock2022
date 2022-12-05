package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.Fournisseur;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.Fournisseur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>  {
    Fournisseur findFournisseurById(Long id);

    @Query(value = "SELECT A.fournisseurImageId FROM Fournisseur A WHERE A.id= :id",
            nativeQuery = true)
    ImageModule getImageModule(@Param("id") Long id);

    @Query(value = "select  u from Fournisseur u where u.mail=:mail and  u.idEntreprise=:idEntreprise")
    Fournisseur findFournisseurByIdEntrepriseAndInformationsF_Mail(String idEntreprise, String mail);

    List<Fournisseur> findAllByIdEntreprise(String idEntreprise, Pageable pageable);
    List<Fournisseur> findAllByIdEntreprise(String idEntreprise);

    Page<Fournisseur> findAllByIdEntrepriseOrderByCreationDate(String IdEntreprise, Pageable pageable);
}
