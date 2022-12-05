/*
package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.ligneCommandeFournisseurDto;
import com.hassan.gestiondestock.model.ligneCommandeClients;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseur {

    public static List<String> validate(ligneCommandeFournisseurDto objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null ){
            erreurList.add("Veuillez renseigner l'article du ligne de commande");
            erreurList.add("Veuillez renseigner le commande correspandante ");
            return erreurList;
        }
        if(objet.getArticleIdCF()==null){
            erreurList.add("Veuillez renseigner l'article du ligne de commande");
        }
        if(objet.getCommandeFournisseursFid()==null){
            erreurList.add("Veuillez renseigner le commande correspandante ");
        }
        return erreurList;
    }
}
*/
