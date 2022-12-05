/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.LigneVenteDto;
import com.hassan.gestiondestock.dto.fournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner le vente pour la ligne du vente");
            erreurList.add("Veuillez renseigner le quantite pour la ligne du vente");
            erreurList.add("Veuillez renseigner le prix unitaire pour la ligne du vente");
            return erreurList;
        }
        if(objet.getIdVente()==null){
            erreurList.add("Veuillez renseigner le vente pour la ligne du vente");
        }
        if(objet.getQuantite()==null){
            erreurList.add("Veuillez renseigner le quantite pour la ligne du vente");
        }
        if(objet.getPrixUnataire()==null){
            erreurList.add("Veuillez renseigner le prix unitaire pour la ligne du vente");
        }
        return erreurList;
    }
}
*/
