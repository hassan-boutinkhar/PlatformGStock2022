/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.CommandeClientsDto;
import com.hassan.gestiondestock.dto.commandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurCommandeValidator {

    public static List<String> validate(commandeFournisseurDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le fournisseur de la commande");
            erreurList.add("Veuillez renseigner le code de la commande");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getCode())){
            erreurList.add("Veuillez renseigner le code de la commande");
        }
        if(objet.getFournisseurid()==null){
            erreurList.add("Veuillez renseigner le fournisseur de la commande");
        }

        return erreurList;
    }
}
*/
