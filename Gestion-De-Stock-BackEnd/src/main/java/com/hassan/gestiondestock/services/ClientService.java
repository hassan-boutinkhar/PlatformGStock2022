package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Repository.ClientRepository;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.Clients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {
    @Autowired
    private ClientRepository clientrepository;
    public List<Clients> findAllData(){
        return clientrepository.findAll();
    }

    public void deleteById(Long id){
        if(id==null){
            System.out.print("client ID IS NULL");
            return ;
        }
        clientrepository.deleteById(id);
    }

    public Clients save1(Clients client) {
        if(client.getId()!=null){
            Clients client1=clientrepository.findClientsById(client.getId());
            ImageModule imageModule=client1.getClientImageId();
            if(imageModule!=null && client.getClientImageId()==null){
                client.setClientImageId(imageModule);
            }
            return clientrepository.save(client);
        }else {
            return clientrepository.save(client);
        }
    }

    public Optional<Clients> findData(Long id) {
        return clientrepository.findById(id);
    }

    public Clients findclientsByIdEntrepriseAndInformationsC_Mail(String idEntreprise, String mail) {
        return clientrepository.findclientsByIdEntrepriseAndInformationsC_Mail(idEntreprise,mail);

    }

    public List<Clients> findAllDataByEntreprise(String idEntreprise, Pageable pageable) {
        return clientrepository.findAllByIdEntreprise(idEntreprise,pageable);
    }

    public int getNumberPages(String IdEntreprise,Pageable pageable){
        Page<Clients> result=clientrepository.findAllByIdEntrepriseOrderByCreationDate(IdEntreprise,pageable);
        return result.getTotalPages();
    }
    public int getNumberOfData(String IdEntreprise){
        List<Clients> clients=clientrepository.findAllByIdEntreprise(IdEntreprise);
        return clients.size();
    }
}
