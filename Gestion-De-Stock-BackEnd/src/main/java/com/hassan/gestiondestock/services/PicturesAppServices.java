package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Mapper.PictureAppMapper;
import com.hassan.gestiondestock.Repository.PicturesAppRepsitory;
import com.hassan.gestiondestock.dto.PictureAppDTO;
import com.hassan.gestiondestock.model.picturesApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PicturesAppServices {

    @Autowired
    private PicturesAppRepsitory pictuersAppRepo;
    @Autowired
    private PictureAppMapper  pictureAppMapper;

    public PictureAppDTO save(MultipartFile photo) throws IOException {
        String fileName= StringUtils.cleanPath(photo.getOriginalFilename());
        PictureAppDTO pictureAppDTO=new PictureAppDTO();
        pictureAppDTO.setNomPhoto(fileName);
        pictureAppDTO.setPhoto(photo.getBytes());
        return pictureAppMapper.toDto(pictuersAppRepo.save(pictureAppMapper.toModel(pictureAppDTO)));
    }

    public Optional<picturesApp> findById(Long id){

        if(id==null){
            System.out.print("ARTICLE ID IS NULL");
            return null;
        }

        return pictuersAppRepo.findById(id);
    }
    public List<PictureAppDTO> findAllData(){
        return pictureAppMapper.toDtos(pictuersAppRepo.findAll());
    }
/*
    public PictureAppDTO findByCodeArticle(String codeArticle){

        if(!StringUtils.hasLength(codeArticle)){
            System.out.print("ARTICLE CODE IS NULL");
            return null;
        }

        return pictureAppMapper.toDto(pictuersAppRepo.findByCodeArticle(codeArticle));
    }


    public void deleteById(Long id){
        if(id==null){
            System.out.print("ARTICLE ID IS NULL");
            return ;
        }
        pictuersAppRepo.deleteById(id);
    }*/
}
