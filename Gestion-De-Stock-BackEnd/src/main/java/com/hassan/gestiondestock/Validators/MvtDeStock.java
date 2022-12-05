package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.mvtDto;

import java.util.ArrayList;
import java.util.List;

public class MvtDeStock {

    public static List<String> validate(mvtDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner l'article de mouvement");
            erreurList.add("Veuillez renseigner la quantite de mouvement");
            return erreurList;
        }
        if(objet.getQuantite()==null){
            erreurList.add("Veuillez renseigner la quantite de mouvement");
        }
        if(objet.getArticleId()==null){
            erreurList.add("Veuillez renseigner l'article de mouvement");
        }
        return erreurList;
    }
}
