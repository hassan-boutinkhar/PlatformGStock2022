
package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Repository.CommandeFournisseurRepository;
import com.hassan.gestiondestock.dto.MailRequest;
import com.hassan.gestiondestock.dto.MailResponse;
import com.hassan.gestiondestock.model.CommandeClient;
import com.hassan.gestiondestock.model.CommandeFournisseurs;
import com.hassan.gestiondestock.model.Fournisseur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CommandeFournisseurService {
    @Autowired
    private CommandeFournisseurRepository commandeFournisseurRepository;
    @Autowired
    private EmailServiceFournisseur service;
    @Autowired
    private FournisseurService fournisseurService;
    public CommandeFournisseurs save(CommandeFournisseurs commandeFournisseur){
        return commandeFournisseurRepository.save(commandeFournisseur);
    }
    public List<CommandeFournisseurs> findAll(){
        return commandeFournisseurRepository.findAll();
    }
    public void deleteById(Long id){
        if(id==null){
            System.out.print("COMMANDE FOURNISSEUR ID IS NULL");
            return ;
        }
        commandeFournisseurRepository.deleteById(id);
    }

    public CommandeFournisseurs findData(Long id) {
        return commandeFournisseurRepository.findCommandeFournisseursById(id);
    }

    public CommandeFournisseurs findByCodecommandeFournisseursAndIdEntreprise(String codecommandeFournisseurs, String idEntreprise) {
        return commandeFournisseurRepository.findCommandeFournisseursByCodeAndAndIdEntreprise(codecommandeFournisseurs,idEntreprise);

    }

    public List<CommandeFournisseurs> findAllDataByEntreprise(String idEntreprise, Pageable pageable) {
        return commandeFournisseurRepository.findAllByIdEntreprise(idEntreprise,pageable);
    }

    public int getNumberPages(String IdEntreprise,Pageable pageable){
        Page<CommandeFournisseurs> result=commandeFournisseurRepository.findAllByIdEntrepriseOrderByCreationDate(IdEntreprise,pageable);
        return result.getTotalPages();
    }
    public MailResponse sendEmail(CommandeFournisseurs commandeFournisseur) {
        Optional<Fournisseur> fournisseur=fournisseurService.findData(Long.valueOf(commandeFournisseur.getIdClient()));
/*        System.out.println(fournisseur.toString());
        System.out.println(fournisseur.get().getMail());
        System.out.println(fournisseur.get().getNom());*/
        MailRequest request=new MailRequest();
        request.setName(fournisseur.get().getNom());
        request.setTo(fournisseur.get().getMail());
        request.setFrom("hassanboutinkhar15@gmail.com");
        request.setSubject("Commande Effectué Avec Succès !!!");
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("NCommande", commandeFournisseur.getCode());
        model.put("location", "Bangalore,India");
        return service.sendEmail(request, model);

    }

    public int getNumberOfData(String idEntreprise) {
        List<CommandeFournisseurs> clients=commandeFournisseurRepository.findAllByIdEntreprise(idEntreprise);
        return clients.size();
    }
}

