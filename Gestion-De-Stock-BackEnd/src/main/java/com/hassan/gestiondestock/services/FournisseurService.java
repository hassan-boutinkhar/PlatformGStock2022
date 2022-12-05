package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Repository.FournisseurRepository;
import com.hassan.gestiondestock.model.CommandeFournisseurs;
import com.hassan.gestiondestock.model.Fournisseur;
import com.hassan.gestiondestock.model.ImageModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurrepository;
    public List<Fournisseur> findAllData(){
        return fournisseurrepository.findAll();
    }

    public void deleteById(Long id){
        if(id==null){
            System.out.print("Fournisseur ID IS NULL");
            return ;
        }
        fournisseurrepository.deleteById(id);
    }

    public Fournisseur save1(Fournisseur fournisseur) {
        if(fournisseur.getId()!=null){
            Fournisseur fournisseur1=fournisseurrepository.findFournisseurById(fournisseur.getId());
            ImageModule imageModule=fournisseur1.getFournisseurImageId();
            if(imageModule!=null && fournisseur.getFournisseurImageId()==null){
                fournisseur.setFournisseurImageId(imageModule);
            }
            return fournisseurrepository.save(fournisseur);
        }else {
            return fournisseurrepository.save(fournisseur);
        }
    }

    public Optional<Fournisseur> findData(Long id) {
        return fournisseurrepository.findById(id);
    }

    public Fournisseur findFournisseurByIdEntrepriseAndInformationsF_Mail(String idEntreprise, String mail) {
        return fournisseurrepository.findFournisseurByIdEntrepriseAndInformationsF_Mail(idEntreprise,mail);

    }

    public List<Fournisseur> findAllDataByEntreprise(String idEntreprise, Pageable pageable) {
        return fournisseurrepository.findAllByIdEntreprise(idEntreprise,pageable);
    }

    public int getNumberPages(String IdEntreprise,Pageable pageable){
        Page<Fournisseur> result=fournisseurrepository.findAllByIdEntrepriseOrderByCreationDate(IdEntreprise,pageable);
        return result.getTotalPages();
    }

    public int getNumberOfData(String idEntreprise) {
            List<Fournisseur> clients=fournisseurrepository.findAllByIdEntreprise(idEntreprise);
            return clients.size();
    }
}
