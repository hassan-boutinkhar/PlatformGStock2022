/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.fournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(fournisseurDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le numero du fournisseur");
            erreurList.add("Veuillez renseigner le nom du fournisseur");
            erreurList.add("Veuillez renseigner l'mail du fournisseur");
            erreurList.add("Veuillez renseigner le prenom du fournisseur");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getInformationsF().getNom())){
            erreurList.add("Veuillez renseigner le nom du fournisseur");
        }
        if(!StringUtils.hasLength(objet.getInformationsF().getPrenom())){
            erreurList.add("Veuillez renseigner le prenom du fournisseur");
        }
        if(!StringUtils.hasLength(objet.getInformationsF().getMail())){
            erreurList.add("Veuillez renseigner l'mail du fournisseur");
        }
        if(!StringUtils.hasLength(objet.getInformationsF().getNumTel())){
            erreurList.add("Veuillez renseigner le numero du fournisseur");
        }
        return erreurList;
    }
}
*/
