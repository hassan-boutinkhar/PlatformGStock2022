package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.CommandeClient;
import com.hassan.gestiondestock.model.CommandeFournisseurs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseurs, Long> {
    CommandeFournisseurs findCommandeFournisseursById(Long id);
    //Optional<commandeFournisseurs> findcommandeFournisseursByCode(String codeCommande);
    CommandeFournisseurs findCommandeFournisseursByCodeAndAndIdEntreprise(String codeCommandeFournisseur, String idEntreprise);
    List<CommandeFournisseurs> findAllByIdEntreprise(String idEntreprise, Pageable pageable);
    List<CommandeFournisseurs> findAllByIdEntreprise(String idEntreprise);

    Page<CommandeFournisseurs> findAllByIdEntrepriseOrderByCreationDate(String idEntreprise, Pageable pageable);

}

