package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.Utilisateurs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long>  {


    Utilisateurs findUtilisateursById(Long id);

    @Query(value = "SELECT A.userImageId FROM Utilisateurs A WHERE A.id= :id",
            nativeQuery = true)
    ImageModule getImageModule(@Param("id") Long id);

    @Query(value = "select  u from Utilisateurs u where u" +
            ".mail=:mail and  u.idEntreprise=:idEntreprise")
    Utilisateurs findUtilisateursByIdEntrepriseAndInfo_Mail(String idEntreprise, String mail);

    List<Utilisateurs> findAllByIdEntreprise(String idEntreprise, Pageable pageable);

    Page<Utilisateurs> findAllByIdEntrepriseOrderByCreationDate(String IdEntreprise, Pageable pageable);
    @Query(value = "select  u from Utilisateurs u where u" +
            ".mail=:mail and  u.motDepass=:motDePass")
    List<Utilisateurs> findUtilisateursByInfo_MailAndMotDepass(String mail,String motDePass);
}
