/*
package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.ventesDto;
import com.hassan.gestiondestock.dto.ventesDto;
import com.hassan.gestiondestock.services.CommandeClientService;
import com.hassan.gestiondestock.services.VentesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/ventes/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/ventes/")
public class VenteController {

    @Autowired
    private VentesService ventesService;

    @PostMapping(value = "addVentes")
    @ApiOperation(value = "Ajouter un vente ou le modifier" , notes = "Cette methode permet de creer un noveau vente ou le modifier",response = ventesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ajouter ou modifier avec succes")
    })
    public ventesDto save(@RequestBody ventesDto commandeclientdto){
        return  ventesService.save(commandeclientdto);
    }

    @GetMapping(value ="id/{id}" )
    @ApiOperation(value = "Rechercher un vente par son ID" , notes = "Cette methode permet de rechercher un methode par son ID",response = ventesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'vente a été trouvé"),
            @ApiResponse(code = 404, message = "L'vente n'a été pas trouvé")
    })
    public ventesDto findById(@PathVariable("id") Integer id){

        return ventesService.findById(id);
    }

    @GetMapping(value = "codeVente/{codeVente}")
    @ApiOperation(value = "Rechercher un vente par son code" , notes = "Cette methode permet de rechercher un methode par son code",response = ventesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'vente a été trouvé"),
            @ApiResponse(code = 404, message = "L'vente n'a été pas trouvé")
    })
    public ventesDto findByCodeArticle(@PathVariable("codeVente") String codeVente){

        return ventesService.findBycodeVente(codeVente);
    }
    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les ventes " , notes = "Cette methode permet de retourner la liste de tout les ventes",responseContainer = "List<ventesDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<ventesDto> findAllData(){
        return ventesService.findAllData();
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un vente par son ID" , notes = "Cette methode permet de supprimer un vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'vente a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Integer id){
        ventesService.deleteById(id);
    }

}
*/
