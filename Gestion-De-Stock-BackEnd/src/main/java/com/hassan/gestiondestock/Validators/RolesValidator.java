/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.RolesDto;
import com.hassan.gestiondestock.dto.mvtDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate(RolesDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner l'utilsateur'");
            erreurList.add("Veuillez renseigner nom du role");
            return erreurList;
        }
        if(objet.getUtilisateurId()==null){
            erreurList.add("Veuillez renseigner l'utilsateur'");
        }
        if(!StringUtils.hasLength(objet.getRoleName())){
            erreurList.add("Veuillez renseigner nom du role");
        }
        return erreurList;
    }
}
*/
