package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<category, Long>  {
    category findByCodeCtegory(String codeCategory);
    List<category> findAllByIdEntreprise(String IdEntreprise, Pageable pageable);
    Page<category> findAllByIdEntrepriseOrderByCreationDate( String IdEntreprise, Pageable pageable);
    category findByCodeCtegoryAndAndIdEntreprise(String codeCategory,String idEntreprise);


}
