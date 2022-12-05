package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.LigneCommandeFournisseurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneComandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseurs, Integer>  {
}
