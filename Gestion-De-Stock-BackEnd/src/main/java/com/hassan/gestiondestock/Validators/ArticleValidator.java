/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.ArticleDto;
import com.hassan.gestiondestock.dto.utilisateursDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le category de l'article");
            erreurList.add("Veuillez renseigner le code de l'article");
            erreurList.add("Veuillez renseigner la designation de l'article");
            erreurList.add("Veuillez renseigner le prix Ht de l'article");
            erreurList.add("Veuillez renseigner le prix TTC de l'article");
            erreurList.add("Veuillez renseigner le prix TVA de l'article");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getCodeArticle())){
            erreurList.add("Veuillez renseigner le code de l'article");
        }
        if(!StringUtils.hasLength(objet.getDesignation())){
            erreurList.add("Veuillez renseigner la designation de l'article");
        }
        if(objet.getPrixUnitaireHt()==null){
            erreurList.add("Veuillez renseigner le prix Ht de l'article");
        }
        if(objet.getTouxTva()==null){
            erreurList.add("Veuillez renseigner le prix TVA de l'article");
        }
        if(objet.getPrixUnitaireTtc()==null){
            erreurList.add("Veuillez renseigner le prix TTC de l'article");
        }
       */
/* if(objet.getCategory()==null){
            erreurList.add("Veuillez renseigner le category de l'article");
        }*//*

        return erreurList;
    }
}
*/
