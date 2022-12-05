/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.CategoryDTO;
import com.hassan.gestiondestock.dto.utilisateursDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilsateurValuidator {

    public static List<String> validate(utilisateursDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le nom de l'utilsateur");
            erreurList.add("Veuillez renseigner le prenom de l'utilsateur");
            erreurList.add("Veuillez renseigner l'mail de l'utilsateur");
            erreurList.add("Veuillez renseigner le mot de pass de l'utilsateur");
            erreurList.add("Veuillez renseigner la date de naissance de l'utilsateur");
            erreurList.add("Veuillez renseigner l'adress de l'utilsateur");
            return erreurList;
        }
        if(!StringUtils.hasLength(objet.getInfo().getNom())){
            erreurList.add("Veuillez renseigner le nom de l'utilsateur");
        }
        if(!StringUtils.hasLength(objet.getInfo().getPrenom())){
            erreurList.add("Veuillez renseigner le prenom de l'utilsateur");
        }
        if(!StringUtils.hasLength(objet.getInfo().getMail())){
            erreurList.add("Veuillez renseigner l'mail de l'utilsateur");
        }
        if(!StringUtils.hasLength(objet.getMotDepass())){
            erreurList.add("Veuillez renseigner le mot de pass de l'utilsateur");
        }
        if(objet.getDateDeNaissance()== null){
            erreurList.add("Veuillez renseigner la date de naissance de l'utilsateur");
        }
        if(objet.getAdressU()== null){
            erreurList.add("Veuillez renseigner l'adress de l'utilsateur");
        }else{
            if(!StringUtils.hasLength(objet.getAdressU().getAdress1())){
                erreurList.add("Veuillez renseigner l'adress 1 de l'utilsateur");
            }
            if(!StringUtils.hasLength(objet.getAdressU().getVille())){
                erreurList.add("Veuillez renseigner la ville de l'utilsateur");
            }
            if(!StringUtils.hasLength(objet.getAdressU().getCodePostale())){
                erreurList.add("Veuillez renseigner le code postale de l'utilsateur");
            }
            if(!StringUtils.hasLength(objet.getAdressU().getPays())){
                erreurList.add("Veuillez renseigner le pays de l'utilsateur");
            }
        }
        return erreurList;
    }
}
*/
