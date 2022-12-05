package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Repository.UtilisateursRepository;
import com.hassan.gestiondestock.dto.MailRequest;
import com.hassan.gestiondestock.dto.MailResponse;
import com.hassan.gestiondestock.model.Clients;
import com.hassan.gestiondestock.model.CommandeClient;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.model.Utilisateurs;
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
public class UtilisateurService {

    @Autowired
    private UtilisateursRepository utilisateurrepository;
    @Autowired
    private EmailServiceUser service;
    public List<Utilisateurs> findAllData(){
        return utilisateurrepository.findAll();
    }

    public void deleteById(Long id){
        if(id==null){
            System.out.print("Utilisateurs ID IS NULL");
            return ;
        }
        utilisateurrepository.deleteById(id);
    }

    public Utilisateurs save1(Utilisateurs user) {
        if(user.getId()!=null){
            Utilisateurs user1=utilisateurrepository.findUtilisateursById(user.getId());
            ImageModule imageModule=user1.getUserImageId();
            if(imageModule!=null && user.getUserImageId()==null){
                user.setUserImageId(imageModule);
            }
            return utilisateurrepository.save(user);
        }else {
            return utilisateurrepository.save(user);
        }
    }

    public Optional<Utilisateurs> findData(Long id) {
        return utilisateurrepository.findById(id);
    }

    public Utilisateurs findUtilisateursByIdEntrepriseAndInformationsF_Mail(String idEntreprise, String mail) {
        return utilisateurrepository.findUtilisateursByIdEntrepriseAndInfo_Mail(idEntreprise,mail);

    }

    public List<Utilisateurs> findAllDataByEntreprise(String idEntreprise, Pageable pageable) {
        return utilisateurrepository.findAllByIdEntreprise(idEntreprise,pageable);
    }

    public int getNumberPages(String IdEntreprise,Pageable pageable){
        Page<Utilisateurs> result=utilisateurrepository.findAllByIdEntrepriseOrderByCreationDate(IdEntreprise,pageable);
        return result.getTotalPages();
    }

    public MailResponse sendEmail(Utilisateurs utilisateur) {
        MailRequest request=new MailRequest();
        request.setName(utilisateur.getNom());
        request.setTo(utilisateur.getMail());
        request.setFrom("hassanboutinkhar15@gmail.com");
        request.setSubject("Votre compte a été créé avec succès  !!");
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("mail", utilisateur.getMail());
        model.put("password", utilisateur.getMotDepass());
        return service.sendEmail(request, model);

    }
    public List<Utilisateurs> findUserByEmailAndPassword(String mail,String motDePass){
        return utilisateurrepository.findUtilisateursByInfo_MailAndMotDepass(mail,motDePass);
    }
}
