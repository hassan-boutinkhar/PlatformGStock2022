package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Clients,Long> {

    Clients findClientsById(Long id);

    @Query(value = "SELECT A.clientImageId FROM clients A WHERE A.id= :id",
            nativeQuery = true)
    ImageModule getImageModule(@Param("id") Long id);


    @Query(value = "select  u from Clients u where u.mail=:mail and  u.idEntreprise=:idEntreprise")
    Clients findclientsByIdEntrepriseAndInformationsC_Mail(String idEntreprise, String mail);

    List<Clients> findAllByIdEntreprise(String idEntreprise, Pageable pageable);
    List<Clients> findAllByIdEntreprise(String idEntreprise);

    Page<Clients> findAllByIdEntrepriseOrderByCreationDate(String IdEntreprise, Pageable pageable);

}
