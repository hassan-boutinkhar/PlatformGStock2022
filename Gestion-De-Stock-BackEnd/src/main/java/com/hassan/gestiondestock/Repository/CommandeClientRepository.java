package com.hassan.gestiondestock.Repository;


import com.hassan.gestiondestock.model.CommandeClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long>  {

    CommandeClient findCommandeClientById(Long id);
    CommandeClient findCommandeClientByCodeAndAndIdEntreprise(String codeCommandeClient, String idEntreprise);


    List<CommandeClient> findAllByIdEntreprise(String idEntreprise, Pageable pageable);
    List<CommandeClient> findAllByIdEntreprise(String idEntreprise);

    Page<CommandeClient> findAllByIdEntrepriseOrderByCreationDate(String idEntreprise, Pageable pageable);
}
