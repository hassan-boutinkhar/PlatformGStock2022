
package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.MailResponse;
import com.hassan.gestiondestock.model.CommandeFournisseurs;
import com.hassan.gestiondestock.model.Fournisseur;
import com.hassan.gestiondestock.model.LigneCommandeFournisseurs;
import com.hassan.gestiondestock.services.CommandeClientPDFExporterF;
import com.hassan.gestiondestock.services.CommandeFournisseurService;
import com.hassan.gestiondestock.services.FournisseurService;
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

@Api(APP_ROOT+"/commandeFournisseur/")//dire que cest un api
@RestController
@RequestMapping(APP_ROOT+"/commandeFournisseur/")
@CrossOrigin(origins = "http://localhost:4200")
public class CommandeFournisseursController {
    @Autowired
    private CommandeFournisseurService commandeFournisseurService;
    @Autowired
    private FournisseurService fournisseurService;
    public Long idCommande;
    public String idFournisseur;



    @PostMapping(value = "addCommandeFournisseurs")
    @ApiOperation(value = "Ajouter un commande fournisseur ou le modifier" , notes = "Cette methode permet de creer un noveau commande fournisseur ou le modifier",response = CommandeFournisseurs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ajouter ou modifier avec succes")
    })
    public CommandeFournisseurs save(@RequestBody CommandeFournisseurs commandefournisseur){
        try {
            System.out.println(
                    commandefournisseur
            );
            MailResponse mailResponse=new MailResponse();
            mailResponse=commandeFournisseurService.sendEmail(commandefournisseur);
            CommandeFournisseurs commandeFournisseurFinale=commandeFournisseurService.save(commandefournisseur);
            this.idCommande = commandeFournisseurFinale.getId();
            this.idFournisseur=commandeFournisseurFinale.getIdClient();
            return  commandeFournisseurFinale;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    @GetMapping(value = "getCommandeById/{id}")
    @ApiOperation(value = "Retourner la liste de tout les commandes de fournisseurs " , notes = "Cette methode permet de retourner la liste de tout les commandes de fournisseurs",response = CommandeFournisseurs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public CommandeFournisseurs findAllData(@PathVariable(value = "id") Long id){
        return commandeFournisseurService.findData(id);
    }
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un commandes de fournisseur par son ID" , notes = "Cette methode permet de supprimer un commandes de fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'commandes de fournisseur a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        commandeFournisseurService.deleteById(id);
    }

    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return commandeFournisseurService.getNumberPages(idEntreprise, PageRequest.of(page, 4));
    }

    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les commandes par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les commandes par Entreprise",responseContainer = "List<CommandeFournisseurs>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<CommandeFournisseurs> findAllDataByEntreprise(@PathVariable String idEntreprise, @RequestParam(defaultValue = "0") int page){
        return commandeFournisseurService.findAllDataByEntreprise(idEntreprise, PageRequest.of(page, 4));
    }

    @GetMapping(value = "getByCodeCommandeAndIdE/{codeCommande}/{idEntreprise}")
    @ApiOperation(value = "Retourner comande unique " , notes = "Cette methode permet de retourner commande",response = CommandeFournisseurs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public CommandeFournisseurs findByCodeArticleAndAndIdEntreprise(@PathVariable("codeCommande") String codeCommande,@PathVariable("idEntreprise") String idEntreprise){
        return commandeFournisseurService.findByCodecommandeFournisseursAndIdEntreprise(codeCommande,idEntreprise);
    }

    @PutMapping(value = {"updateCommande"})
    @ApiOperation(value = "Mise a jour ou bien modifier un commande" , notes = "Cette methode permet de modifier un commande",response = CommandeFournisseurs.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes "),
    })
    public CommandeFournisseurs updateArticle(@RequestBody CommandeFournisseurs commandefournisseur){
        return commandeFournisseurService.save(commandefournisseur);
    }

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        CommandeFournisseurs commandeFournisseur=commandeFournisseurService.findData(idCommande);
        //System.out.println(commandeFournisseur.toString());

        List<LigneCommandeFournisseurs> listUsers = commandeFournisseur.getLigneCommandeClients();
        System.out.println(listUsers.toString());
        Optional<Fournisseur> fournisseurs=fournisseurService.findData(Long.valueOf(this.idFournisseur));


            CommandeClientPDFExporterF exporter = new CommandeClientPDFExporterF(commandeFournisseur,fournisseurs,listUsers);
        exporter.export(response);

    }
    @GetMapping(value = "getNumberData/{idEntreprise}")
    public int getNumberOfData(@PathVariable String idEntreprise){
        return commandeFournisseurService.getNumberOfData(idEntreprise);
    }


}

