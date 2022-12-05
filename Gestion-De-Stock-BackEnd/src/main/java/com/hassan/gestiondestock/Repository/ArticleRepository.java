package com.hassan.gestiondestock.Repository;

import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article  findArticlesById(Long id);


    @Query(value = "SELECT A.articleImage FROM Article A WHERE A.id= :id",
            nativeQuery = true)
    ImageModule getImageModule(@Param("id") Long id);

    Article findArticlesByCodeArticleAndAndIdEntreprise(String codeArticle, String idEntreprise);

    List<Article> findAllByIdEntreprise(String idEntreprise, Pageable pageable);

    Page<Article> findAllByIdEntrepriseOrderByCreationDate( String IdEntreprise, Pageable pageable);



/*    @Query(value = "select * from Article where codeArticle =: code", nativeQuery = true)
    List<Article> findArticlesByCode(@Param("code") String c); // si le code qui se trouve dans la requete est le meme dans la methode alors nous ne sommes oubliges de mettre @Parm..*/
}
