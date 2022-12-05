package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.MailResponse;
import com.hassan.gestiondestock.model.CommandeClient;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.LoginData;
import com.hassan.gestiondestock.model.Utilisateurs;
import com.hassan.gestiondestock.services.UtilisateurService;
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
import java.util.Map;
import java.util.Optional;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/utilisateur/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/utilisateur/")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurservice;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = {"addNewUtilisateurs"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Utilisateurs addNewUtilisateurs(@RequestPart("user") Utilisateurs utilisateur, @RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                utilisateur.setUserImageId(imageModule);
                MailResponse mailResponse=new MailResponse();
                mailResponse=utilisateurservice.sendEmail(utilisateur);
                return utilisateurservice.save1(utilisateur);
            }else{
                MailResponse mailResponse=new MailResponse();
                mailResponse=utilisateurservice.sendEmail(utilisateur);
                return utilisateurservice.save1(utilisateur);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "utilisateurId/{id}")
    @ApiOperation(value = "Retourner la liste de tout les Utilisateurs " , notes = "Cette methode permet de retourner la liste de tout les Utilisateurs",responseContainer = "List<Utilisateurs>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Optional<Utilisateurs> getData(@PathVariable Long id){
        try{
            System.out.println("hhhhh");
            return utilisateurservice.findData(id);
        }catch (Exception e){
            System.out.println("ERROR");

            System.out.print(e.getMessage());
            return null;
        }

    }
    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les Utilisateurs " , notes = "Cette methode permet de retourner la liste de tout les Utilisateurs",responseContainer = "List<Utilisateurs>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Utilisateurs> findAllData(){
        return utilisateurservice.findAllData();
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un utilisateur par son ID" , notes = "Cette methode permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        utilisateurservice.deleteById(id);
    }

    public ImageModule uploadImage(@Nullable MultipartFile file ) throws IOException {
        ImageModule imageModule=new ImageModule(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        return imageModule;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = {"updateUtilisateurs"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Utilisateurs updateUtilisateurs(@RequestPart("user")Utilisateurs utilisateur,@RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                utilisateur.setUserImageId(imageModule);
                return utilisateurservice.save1(utilisateur);
            }else{
                return utilisateurservice.save1(utilisateur);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "getByMailUtilisateursAndIdE/{mail}/{idEntreprise}")
    @ApiOperation(value = "Retourner utilisateur unique " , notes = "Cette methode permet de retourner utilisateur",response = Utilisateurs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Utilisateurs findUtilisateursByMailAndAndIdEntreprise(@PathVariable("mail") String mail,@PathVariable("idEntreprise") String idEntreprise){
        return utilisateurservice.findUtilisateursByIdEntrepriseAndInformationsF_Mail(idEntreprise,mail);
    }

    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les Utilisateurs par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les Utilisateurs par Entreprise",responseContainer = "List<Utilisateurs>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Utilisateurs> findAllDataByEntreprise(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0") int page){
        return utilisateurservice.findAllDataByEntreprise(idEntreprise, PageRequest.of(page, 4));
    }

    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return utilisateurservice.getNumberPages(idEntreprise,PageRequest.of(page, 4));
    }
    @GetMapping(value = "findUserLogin/{mail}/{passsword}")
    public List<Utilisateurs> findUserLogin(@PathVariable("mail") String mail,@PathVariable("passsword") String passsword){

        return utilisateurservice.findUserByEmailAndPassword(mail, passsword);
    }
}
