/*
package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Exeptions.ErrorsCodes;
import com.hassan.gestiondestock.Exeptions.InvalidEntityExecption;
import com.hassan.gestiondestock.Mapper.EntrepriseMapper;
import com.hassan.gestiondestock.Repository.EntrepriseRepository;
import com.hassan.gestiondestock.Validators.EntrepriseValidator;
import com.hassan.gestiondestock.dto.EntrepriseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriserepository;
    @Autowired
    private EntrepriseMapper entreprisemapper;

    public EntrepriseDto save(EntrepriseDto entreprisedto){
        List<String> errors = EntrepriseValidator.validate(entreprisedto);
        if(!errors.isEmpty()){
            System.out.print("ENTREPRISE IS NOT VALID");
            throw new InvalidEntityExecption("ENTREPRISE N'EST PAS VALID", ErrorsCodes.ENTREPRISE_NOT_VALID_FOUND,errors);
        }
        return entreprisemapper.toDto(entrepriserepository.save(entreprisemapper.toModel(entreprisedto)));
    }

    public EntrepriseDto findById(Integer id){

        if(id==null){
            System.out.print("ENTREPRISE ID IS NULL");
            return null;
        }

        return entreprisemapper.toDto(entrepriserepository.findById(id));
    }

    public List<EntrepriseDto> findAllData(){
        return entreprisemapper.toDtos(entrepriserepository.findAll());
    }

    public void deleteById(Integer id){
        if(id==null){
            System.out.print("ENTREPRISE ID IS NULL");
            return ;
        }
        entrepriserepository.deleteById(id);
    }
}
*/
