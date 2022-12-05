/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.ventesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate(ventesDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le commantaire du vente");
            erreurList.add("Veuillez renseigner le code vente");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getCommantaire())){
            erreurList.add("Veuillez renseigner le commantaire du vente");
        }
        if(!StringUtils.hasLength(objet.getCode())){
            erreurList.add("Veuillez renseigner le code vente");
        }
        return erreurList;
    }
}
*/
