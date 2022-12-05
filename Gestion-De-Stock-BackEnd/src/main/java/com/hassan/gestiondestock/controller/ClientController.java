package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.Clients;
import com.hassan.gestiondestock.services.ClientService;
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

@Api(APP_ROOT+"/client/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/client/")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    private ClientService clientservice;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = {"addNewClient"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Clients addNewclients(@RequestPart("client") Clients client, @RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                client.setClientImageId(imageModule);
                return clientservice.save1(client);
            }else{
                return clientservice.save1(client);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "clientId/{id}")
    @ApiOperation(value = "Retourner la liste de tout les clients " , notes = "Cette methode permet de retourner la liste de tout les clients",responseContainer = "List<clients>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Optional<Clients> getData(@PathVariable Long id){
        try{
            System.out.println("hhhhh");
            return clientservice.findData(id);
        }catch (Exception e){
            System.out.println("ERROR");

            System.out.print(e.getMessage());
            return null;
        }

    }
    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les clients " , notes = "Cette methode permet de retourner la liste de tout les clients",responseContainer = "List<clients>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Clients> findAllData(){
        return clientservice.findAllData();
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un client par son ID" , notes = "Cette methode permet de supprimer un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'client a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        clientservice.deleteById(id);
    }

    public ImageModule uploadImage(@Nullable MultipartFile file ) throws IOException {
        ImageModule imageModule=new ImageModule(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        return imageModule;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = {"updateclients"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Clients updateclients(@RequestPart("client") Clients client, @RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                client.setClientImageId(imageModule);
                return clientservice.save1(client);
            }else{
                return clientservice.save1(client);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "getByMailclientsAndIdE/{mail}/{idEntreprise}")
    @ApiOperation(value = "Retourner client unique " , notes = "Cette methode permet de retourner client",response = Clients.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Clients findclientsByMailAndAndIdEntreprise(@PathVariable("mail") String mail, @PathVariable("idEntreprise") String idEntreprise){
        return clientservice.findclientsByIdEntrepriseAndInformationsC_Mail(idEntreprise,mail);
    }

    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les clients par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les clients par Entreprise",responseContainer = "List<clients>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Clients> findAllDataByEntreprise(@PathVariable String idEntreprise, @RequestParam(defaultValue = "0") int page){
        return clientservice.findAllDataByEntreprise(idEntreprise, PageRequest.of(page, 4));
    }

    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return clientservice.getNumberPages(idEntreprise,PageRequest.of(page, 4));
    }
    @GetMapping(value = "getNumberData/{idEntreprise}")
    public int getNumberOfData(@PathVariable String idEntreprise){
        return clientservice.getNumberOfData(idEntreprise);
    }
}
