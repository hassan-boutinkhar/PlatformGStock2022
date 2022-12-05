package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.MailResponse;
import com.hassan.gestiondestock.model.Clients;
import com.hassan.gestiondestock.model.CommandeClient;
import com.hassan.gestiondestock.model.ligneCommandeClients;
import com.hassan.gestiondestock.services.ClientService;
import com.hassan.gestiondestock.services.CommandeClientPDFExporter;
import com.hassan.gestiondestock.services.CommandeClientService;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/commandeClient/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/commandeClient/")
@CrossOrigin(origins = "http://localhost:4200")

public class CommandeClientController {
    @Autowired
    private CommandeClientService commandeClientsService;
    @Autowired
    private ClientService clientService;
    public Long idCommande;
    public String idClient;



    @PostMapping(value = "addCommandeClient")
    @ApiOperation(value = "Ajouter un commande client ou le modifier" , notes = "Cette methode permet de creer un noveau commande client ou le modifier",response = CommandeClient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ajouter ou modifier avec succes")
    })
    public CommandeClient save(@RequestBody CommandeClient commandeclient){
        try {
            MailResponse mailResponse=new MailResponse();
            mailResponse=commandeClientsService.sendEmail(commandeclient);
            CommandeClient commandeClientFinale=commandeClientsService.save(commandeclient);
            this.idCommande = commandeClientFinale.getId();
            this.idClient=commandeClientFinale.getIdClient();
            return  commandeClientFinale;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    @GetMapping(value = "getCommandeById/{id}")
    @ApiOperation(value = "Retourner la liste de tout les commandes de clients " , notes = "Cette methode permet de retourner la liste de tout les commandes de clients",response = CommandeClient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public CommandeClient findAllData(@PathVariable(value = "id") Long id){
        return commandeClientsService.findData(id);
    }
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un commandes de client par son ID" , notes = "Cette methode permet de supprimer un commandes de client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'commandes de client a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        commandeClientsService.deleteById(id);
    }
    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return commandeClientsService.getNumberPages(idEntreprise, PageRequest.of(page, 4));
    }
    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les commandes par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les commandes par Entreprise",responseContainer = "List<CommandeClient>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<CommandeClient> findAllDataByEntreprise(@PathVariable String idEntreprise, @RequestParam(defaultValue = "0") int page){
        return commandeClientsService.findAllDataByEntreprise(idEntreprise, PageRequest.of(page, 4));
    }
    @GetMapping(value = "getByCodeCommandeAndIdE/{codeCommande}/{idEntreprise}")
    @ApiOperation(value = "Retourner comande unique " , notes = "Cette methode permet de retourner commande",response = CommandeClient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public CommandeClient findByCodeArticleAndAndIdEntreprise(@PathVariable("codeCommande") String codeCommande,@PathVariable("idEntreprise") String idEntreprise){
        return commandeClientsService.findByCodeCommandeClientAndAndIdEntreprise(codeCommande,idEntreprise);
    }
    @PutMapping(value = {"updateCommande"})
    @ApiOperation(value = "Mise a jour ou bien modifier un commande" , notes = "Cette methode permet de modifier un commande",response = CommandeClient.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes "),
    })
    public CommandeClient updateArticle(@RequestBody CommandeClient commandeclient){
        return commandeClientsService.save(commandeclient);
    }
    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        CommandeClient commandeClient=commandeClientsService.findData(idCommande);
        //System.out.println(commandeClient.toString());

        List<ligneCommandeClients> listUsers = commandeClient.getLigneCommandeClients();
        System.out.println(listUsers.toString());
        Optional<Clients> clients=clientService.findData(Long.valueOf(this.idClient));


        CommandeClientPDFExporter exporter = new CommandeClientPDFExporter(listUsers,commandeClient,clients);
        exporter.export(response);

    }
    @GetMapping(value = "getNumberData/{idEntreprise}")
    public int getNumberOfData(@PathVariable String idEntreprise){
        return commandeClientsService.getNumberOfData(idEntreprise);
    }

}

