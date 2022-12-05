package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.model.Fournisseur;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.services.FournisseurService;
import com.sun.istack.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/fournisseur/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/fournisseur/")
@CrossOrigin(origins = "http://localhost:4200")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurservice;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = {"addNewFournisseur"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Fournisseur addNewFournisseur(@RequestPart("fournisseur") Fournisseur fournisseur, @RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                fournisseur.setFournisseurImageId(imageModule);
                return fournisseurservice.save1(fournisseur);
            }else{
                return fournisseurservice.save1(fournisseur);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "fournisseurId/{id}")
    @ApiOperation(value = "Retourner la liste de tout les Fournisseur " , notes = "Cette methode permet de retourner la liste de tout les Fournisseur",responseContainer = "List<Fournisseur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Optional<Fournisseur> getData(@PathVariable Long id){
        try{
            System.out.println("hhhhh");
            return fournisseurservice.findData(id);
        }catch (Exception e){
            System.out.println("ERROR");

            System.out.print(e.getMessage());
            return null;
        }

    }
    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les Fournisseur " , notes = "Cette methode permet de retourner la liste de tout les Fournisseur",responseContainer = "List<Fournisseur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Fournisseur> findAllData(){
        return fournisseurservice.findAllData();
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un fournisseur par son ID" , notes = "Cette methode permet de supprimer un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'fournisseur a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        fournisseurservice.deleteById(id);
    }

    public ImageModule uploadImage(@Nullable MultipartFile file ) throws IOException {
        ImageModule imageModule=new ImageModule(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        return imageModule;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = {"updateFournisseur"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Fournisseur updateFournisseur(@RequestPart("fournisseur")Fournisseur fournisseur,@RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                fournisseur.setFournisseurImageId(imageModule);
                return fournisseurservice.save1(fournisseur);
            }else{
                return fournisseurservice.save1(fournisseur);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "getByMailFournisseurAndIdE/{mail}/{idEntreprise}")
    @ApiOperation(value = "Retourner fournisseur unique " , notes = "Cette methode permet de retourner fournisseur",response = Fournisseur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Fournisseur findFournisseurByMailAndAndIdEntreprise(@PathVariable("mail") String mail,@PathVariable("idEntreprise") String idEntreprise){
        return fournisseurservice.findFournisseurByIdEntrepriseAndInformationsF_Mail(idEntreprise,mail);
    }

    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les Fournisseur par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les Fournisseur par Entreprise",responseContainer = "List<Fournisseur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Fournisseur> findAllDataByEntreprise(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0") int page){
        return fournisseurservice.findAllDataByEntreprise(idEntreprise, PageRequest.of(page, 4));
    }

    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return fournisseurservice.getNumberPages(idEntreprise,PageRequest.of(page, 4));
    }
    @GetMapping(value = "getNumberData/{idEntreprise}")
    public int getNumberOfData(@PathVariable String idEntreprise){
        return fournisseurservice.getNumberOfData(idEntreprise);
    }
}
