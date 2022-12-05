package com.hassan.gestiondestock.Validators;

import com.hassan.gestiondestock.dto.CategoryDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDTO objet){
        List<String> erreurList= new ArrayList<>();
        if(objet==null || !StringUtils.hasLength(objet.getCodeCtegory())){
            erreurList.add("Veuillez renseigner le code de la categorie");
        }
        return erreurList;
    }
}
