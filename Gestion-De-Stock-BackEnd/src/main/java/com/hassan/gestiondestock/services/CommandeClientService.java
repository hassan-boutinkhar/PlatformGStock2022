
package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Repository.CommandeClientRepository;
import com.hassan.gestiondestock.dto.MailRequest;
import com.hassan.gestiondestock.dto.MailResponse;
import com.hassan.gestiondestock.model.Clients;
import com.hassan.gestiondestock.model.CommandeClient;
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
public class CommandeClientService {
    @Autowired
    private CommandeClientRepository commandeClientRepositorey;
    @Autowired
    private EmailService service;
    @Autowired
    private ClientService clientService;
    public CommandeClient save(CommandeClient commandeclient){
        return commandeClientRepositorey.save(commandeclient);
    }
    public List<CommandeClient> findAll(){
        return commandeClientRepositorey.findAll();
    }
    public void deleteById(Long id){
        if(id==null){
            System.out.print("COMMANDE CLIENT ID IS NULL");
            return ;
        }
        commandeClientRepositorey.deleteById(id);
    }

    public CommandeClient findData(Long id) {
        return commandeClientRepositorey.findCommandeClientById(id);
    }

    public CommandeClient findByCodeCommandeClientAndAndIdEntreprise(String codeCommandeClient, String idEntreprise) {
        return commandeClientRepositorey.findCommandeClientByCodeAndAndIdEntreprise(codeCommandeClient,idEntreprise);

    }

    public List<CommandeClient> findAllDataByEntreprise(String idEntreprise, Pageable pageable) {
        return commandeClientRepositorey.findAllByIdEntreprise(idEntreprise,pageable);
    }

    public int getNumberPages(String IdEntreprise,Pageable pageable){
        Page<CommandeClient> result=commandeClientRepositorey.findAllByIdEntrepriseOrderByCreationDate(IdEntreprise,pageable);
        return result.getTotalPages();
    }
    public MailResponse sendEmail(CommandeClient commandeclient) {
        Optional<Clients> clients=clientService.findData(Long.valueOf(commandeclient.getIdClient()));
/*        System.out.println(clients.toString());
        System.out.println(clients.get().getMail());
        System.out.println(clients.get().getNom());*/
        MailRequest request=new MailRequest();
        request.setName(clients.get().getNom());
        request.setTo(clients.get().getMail());
        request.setFrom("hassanboutinkhar15@gmail.com");
        request.setSubject("Commande Effectué Avec Succès !!!");
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("NCommande", commandeclient.getCode());
        model.put("location", "Bangalore,India");
        return service.sendEmail(request, model);

    }

    public int getNumberOfData(String idEntreprise) {
        List<CommandeClient> clients=commandeClientRepositorey.findAllByIdEntreprise(idEntreprise);
        return clients.size();
    }
}

