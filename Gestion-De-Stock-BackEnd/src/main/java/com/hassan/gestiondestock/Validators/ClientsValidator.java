/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.ArticleDto;
import com.hassan.gestiondestock.dto.clientsDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientsValidator {

    public static List<String> validate(clientsDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le numero du client");
            erreurList.add("Veuillez renseigner le nom du client");
            erreurList.add("Veuillez renseigner l'mail du client");
            erreurList.add("Veuillez renseigner le prenom du client");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getInformationsC().getNom())){
            erreurList.add("Veuillez renseigner le nom du client");
        }
        if(!StringUtils.hasLength(objet.getInformationsC().getPrenom())){
            erreurList.add("Veuillez renseigner le prenom du client");
        }
        if(!StringUtils.hasLength(objet.getInformationsC().getMail())){
            erreurList.add("Veuillez renseigner l'mail du client");
        }
        if(!StringUtils.hasLength(objet.getInformationsC().getNumTel())){
            erreurList.add("Veuillez renseigner le numero du client");
        }
        return erreurList;
    }
}
*/
