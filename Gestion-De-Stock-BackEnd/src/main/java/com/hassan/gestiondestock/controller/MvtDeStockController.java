package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.mvtDto;
import com.hassan.gestiondestock.services.MvtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/gestionDeStock/v1/api/mvt/")//dire que cest un api
@RestController
@RequestMapping("/gestionDeStock/v1/api/mvt/")
@CrossOrigin(origins = "http://localhost:4200")
public class MvtDeStockController {

    @Autowired
    private MvtService mvtService;

    @PostMapping(value = "addMvt")
    @ApiOperation(value = "Ajouter un movement de stock ou le modifier" , notes = "Cette methode permet de creer un noveau movement de stock ou le modifier",response = mvtDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ajouter avec succes")
    })
    public mvtDto save(@RequestBody mvtDto mvtdto){
        System.out.print("The addition of 1");
        return  mvtService.save(mvtdto);
    }
    @GetMapping(value = "allMvt/{IdEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les mvt par E/se " , notes = "Cette methode permet de retourner la liste de tout les mvt par E/se",responseContainer = "List<mvtDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<mvtDto> findAllData(@PathVariable("IdEntreprise") String IdEntreprise){
        return mvtService.getAllData(IdEntreprise);
    }
    @GetMapping(value = "lastMvt/{IdEntreprise}/{IdArticle}")
    @ApiOperation(value = "Retourner la liste de retourner le dernier mvt par E/se et idArticle" , notes = "Retourner la liste de retourner le dernier mvt par E/se et idArticle\"",responseContainer = "mvtDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public mvtDto findLastMvt(@PathVariable("IdEntreprise") String IdEntreprise,@PathVariable("IdArticle") Long IdArticle){
        return mvtService.findLastMvt(IdEntreprise,IdArticle);
    }
    @GetMapping(value = "movementsArticle/{IdEntreprise}/{IdArticle}")
    @ApiOperation(value = "Retourner la liste de retourner le dernier mvt par E/se et idArticle" , notes = "Retourner la liste de retourner le dernier mvt par E/se et idArticle\"",responseContainer = "mvtDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<mvtDto> findArticleMvt(@PathVariable("IdEntreprise") String IdEntreprise,@PathVariable("IdArticle") Long IdArticle){
        return mvtService.findArtcileMvts(IdEntreprise,IdArticle);
    }
    @GetMapping(value = "movementsArticleEmpty/{IdEntreprise}/{IdArticle}")
    @ApiOperation(value = "Retourner la liste de retourner le dernier mvt par E/se et idArticle" , notes = "Retourner la liste de retourner le dernier mvt par E/se et idArticle\"",responseContainer = "mvtDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public boolean mvtIsEmpty(@PathVariable("IdEntreprise") String IdEntreprise,@PathVariable("IdArticle") Long IdArticle){
        return mvtService.mvtIsEmpty(IdEntreprise,IdArticle);
    }
}
