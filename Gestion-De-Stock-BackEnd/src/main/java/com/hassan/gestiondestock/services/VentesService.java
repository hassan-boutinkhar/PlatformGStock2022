/*
package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Exeptions.ErrorsCodes;
import com.hassan.gestiondestock.Exeptions.InvalidEntityExecption;
import com.hassan.gestiondestock.Mapper.LigneVenteMapper;
import com.hassan.gestiondestock.Mapper.VentesMapper;
import com.hassan.gestiondestock.Repository.LigneVenteRepository;
import com.hassan.gestiondestock.Repository.VentesRepository;
import com.hassan.gestiondestock.Validators.VenteValidator;
import com.hassan.gestiondestock.dto.ventesDto;
import com.hassan.gestiondestock.model.LigneVente;
import com.hassan.gestiondestock.model.Ventes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class VentesService {

    @Autowired
    private VentesRepository ventesrepository;
    @Autowired
    private LigneVenteRepository ligneventerepository;
    @Autowired
    private VentesMapper ventesmapper;
    @Autowired
    private LigneVenteMapper ligneventesmapper;

    public ventesDto save(ventesDto ventedto){
        List<String> errors = VenteValidator.validate(ventedto);
        if(!errors.isEmpty()){
            System.out.print("VENTE IS NOT VALID");
            throw new InvalidEntityExecption("VENTE N'EST PAS VALID", ErrorsCodes.VENTE_NOT_VALID_FOUND,errors);
        }
        Ventes objet=ventesrepository.save(ventesmapper.toModel(ventedto));
        ventedto.getLigneVente().forEach(
                data->{
                    LigneVente objetLc=ligneventesmapper.toModel(data);
                    objetLc.setIdVente(objet);
                    ligneventerepository.save(objetLc);
                }
        );

        return ventesmapper.toDto(objet);
    }

    public ventesDto findById(Integer id){

        if(id==null){
            System.out.print("VENTE ID IS NULL");
            return null;
        }

        return ventesmapper.toDto(ventesrepository.findById(id));
    }

    public ventesDto findBycodeVente(String codeVente){

        if(!StringUtils.hasLength(codeVente)){
            System.out.print("VENTE CODE IS NULL");
            return null;
        }

        return ventesmapper.toDto(ventesrepository.findVentesByCode(codeVente));
    }
    public List<ventesDto> findAllData(){
        return ventesmapper.toDtos(ventesrepository.findAll());
    }

    public void deleteById(Integer id){
        if(id==null){
            System.out.print("VENTE ID IS NULL");
            return ;
        }
        ventesrepository.deleteById(id);
    }
}
*/
