package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.ArticleDto;
import com.hassan.gestiondestock.dto.PictureAppDTO;
import com.hassan.gestiondestock.model.picturesApp;
import com.hassan.gestiondestock.services.ArticleService;
import com.hassan.gestiondestock.services.PicturesAppServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/picture/")//dire que cest un api
@RestController//GENRER SWAGGER.JSON http://localhost:8080/v2/api-docs
@RequestMapping(APP_ROOT+"/picture/")
@CrossOrigin(origins = "http://localhost:4200")
public class PictureAppController {
    @Autowired
    private PicturesAppServices picturesAppServices;


    @PostMapping(value = "addPicture")

    public PictureAppDTO save(@RequestParam("file") MultipartFile file ) throws IOException {
        return  picturesAppServices.save(file);
    }
    @GetMapping(value = "getPitcure/{id}")
    public Optional<picturesApp> getPicture(@PathVariable("id") Long id){
        return picturesAppServices.findById(id);
    }
    @GetMapping(value = "getAllPicture")
    public List<PictureAppDTO> getPicture(){
        return picturesAppServices.findAllData();
    }
}
