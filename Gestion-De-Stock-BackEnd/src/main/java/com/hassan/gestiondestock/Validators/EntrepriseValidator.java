/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.CommandeClientsDto;
import com.hassan.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le nom de l'entreprise");
            erreurList.add("Veuillez renseigner la description de l'entreprise");
            erreurList.add("Veuillez renseigner le code fiscale de l'entreprise");
            erreurList.add("Veuillez renseigner le email de l'entreprise");
            erreurList.add("Veuillez renseigner le numero de l'entreprise");
            erreurList.add("Veuillez renseigner l'adress de l'entreprise");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getNom())){
            erreurList.add("Veuillez renseigner le nom de l'entreprise");
        }
        if(!StringUtils.hasLength(objet.getDescription())){
            erreurList.add("Veuillez renseigner la description de l'entreprise");
        }
        if(!StringUtils.hasLength(objet.getCodeFiscale())){
            erreurList.add("Veuillez renseigner le code fiscale de l'entreprise");
        }
        if(!StringUtils.hasLength(objet.getEmail())){
            erreurList.add("Veuillez renseigner le email de l'entreprise");
        }
        if(!StringUtils.hasLength(objet.getNumTel())){
            erreurList.add("Veuillez renseigner le numero de l'entreprise");
        }
        if(objet.getAdress()== null){
            erreurList.add("Veuillez renseigner l'adress de l'entreprise");
        }else{
            if(!StringUtils.hasLength(objet.getAdress().getAdress1())){
                erreurList.add("Veuillez renseigner l'adress 1 de l'entreprise");
            }
            if(!StringUtils.hasLength(objet.getAdress().getVille())){
                erreurList.add("Veuillez renseigner la ville de l'entreprise");
            }
            if(!StringUtils.hasLength(objet.getAdress().getCodePostale())){
                erreurList.add("Veuillez renseigner le code postale de l'entreprise");
            }
            if(!StringUtils.hasLength(objet.getAdress().getPays())){
                erreurList.add("Veuillez renseigner le pays de l'entreprise");
            }
        }

        return erreurList;
    }
}
*/
