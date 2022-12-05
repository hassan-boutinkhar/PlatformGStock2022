package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Repository.ArticleRepository;
import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.ImageModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAllData(){
        return articleRepository.findAll();
    }

    public void deleteById(Long id){
        if(id==null){
            System.out.print("ARTICLE ID IS NULL");
            return ;
        }
         articleRepository.deleteById(id);
    }

    public Article save1(Article article) {
        if(article.getId()!=null){
            Article article1=articleRepository.findArticlesById(article.getId());
            ImageModule imageModule=article1.getArticleImage();
            if(imageModule!=null){
                article.setArticleImage(imageModule);
            }
            return articleRepository.save(article);
        }else {
            return articleRepository.save(article);
        }
    }

    public Optional<Article> findData(Long id) {
        return articleRepository.findById(id);
    }

    public Article findByCodeArticleAndAndIdEntreprise(String codeArticle, String idEntreprise) {
        return articleRepository.findArticlesByCodeArticleAndAndIdEntreprise(codeArticle,idEntreprise);

    }

    public List<Article> findAllDataByEntreprise(String idEntreprise, Pageable pageable) {
        return articleRepository.findAllByIdEntreprise(idEntreprise,pageable);
    }

    public int getNumberPages(String IdEntreprise,Pageable pageable){
        Page<Article> result=articleRepository.findAllByIdEntrepriseOrderByCreationDate(IdEntreprise,pageable);
        return result.getTotalPages();
    }
}
