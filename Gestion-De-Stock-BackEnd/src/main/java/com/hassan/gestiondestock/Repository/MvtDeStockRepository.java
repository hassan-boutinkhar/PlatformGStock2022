package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.MvtDeStocks;
import com.hassan.gestiondestock.model.category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MvtDeStockRepository extends JpaRepository<MvtDeStocks, Long>  {
    List<MvtDeStocks> findAllByIdEntreprise(String idEntreprise);
    List<MvtDeStocks> findAllByIdEntrepriseAndArticleIdOrderByCreationDateDesc(String idEntreprise, Long idArticle);

}
