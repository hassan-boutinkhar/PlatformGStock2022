package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer>  {
}
