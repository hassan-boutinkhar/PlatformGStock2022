/*
package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.EntrepriseDto;
import com.hassan.gestiondestock.services.EntrepriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/entreprise/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/entreprise/")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseservice;

    @PostMapping(value = "addEntreprise")
    @ApiOperation(value = "Ajouter un entreprise ou le modifier" , notes = "Cette methode permet de creer un noveau entreprise ou le modifier",response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ajouter ou modifier avec succes")
    })
    public EntrepriseDto save(@RequestBody EntrepriseDto entreprisedto){
        return  entrepriseservice.save(entreprisedto);
    }

    @GetMapping(value ="id/{id}" )
    @ApiOperation(value = "Rechercher un entreprise par son ID" , notes = "Cette methode permet de rechercher un methode par son ID",response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a été trouvé"),
            @ApiResponse(code = 404, message = "L'entreprise n'a été pas trouvé")
    })
    public EntrepriseDto findById(@PathVariable("id") Integer id){

        return entrepriseservice.findById(id);
    }

    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les entreprises " , notes = "Cette methode permet de retourner la liste de tout les entreprises",responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<EntrepriseDto> findAllData(){
        return entrepriseservice.findAllData();
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un entreprise par son ID" , notes = "Cette methode permet de supprimer un entreprise par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Integer id){
        entrepriseservice.deleteById(id);
    }
}
*/
