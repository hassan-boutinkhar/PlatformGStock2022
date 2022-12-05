package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.dto.ArticleDto;
import com.hassan.gestiondestock.dto.CategoryDTO;
import com.hassan.gestiondestock.model.Article;
import com.hassan.gestiondestock.model.ImageModule;
import com.hassan.gestiondestock.services.ArticleService;
import com.sun.istack.Nullable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.bytebuddy.utility.nullability.NeverNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.hassan.gestiondestock.Utils.Contstants.APP_ROOT;

@Api(APP_ROOT+"/article/")//dire que cest un api
@RestController//GENRER SWAGGER.JSON http://localhost:8080/v2/api-docs
@RequestMapping(APP_ROOT+"/article/")
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = {"addNewArticle"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Article addNewArticle(@RequestPart("article")Article article,@RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
            ImageModule imageModule=uploadImage(file);
            article.setArticleImage(imageModule);
            return articleService.save1(article);
            }else{
                return articleService.save1(article);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "articleId/{id}")
    @ApiOperation(value = "Retourner la liste de tout les articles " , notes = "Cette methode permet de retourner la liste de tout les articles",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Optional<Article> getData(@PathVariable Long id){
        try{
            System.out.println("hhhhh");
            return articleService.findData(id);
        }catch (Exception e){
            System.out.println("ERROR");

            System.out.print(e.getMessage());
             return null;
        }

    }
    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les articles " , notes = "Cette methode permet de retourner la liste de tout les articles",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Article> findAllData(){
        return articleService.findAllData();
    }

    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un article par son ID" , notes = "Cette methode permet de supprimer un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        articleService.deleteById(id);
    }

    public ImageModule uploadImage(@Nullable MultipartFile file ) throws IOException{
        ImageModule imageModule=new ImageModule(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        return imageModule;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = {"updateArticle"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Article updateArticle(@RequestPart("article")Article article,@RequestPart(value = "imageFile", required=false) MultipartFile file){

        try{
            if(file!=null){
                ImageModule imageModule=uploadImage(file);
                article.setArticleImage(imageModule);
                return articleService.save1(article);
            }else{
                return articleService.save1(article);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping(value = "getByCodeArticleAndIdE/{codeArticle}/{idEntreprise}")
    @ApiOperation(value = "Retourner article unique " , notes = "Cette methode permet de retourner article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Article findByCodeArticleAndAndIdEntreprise(@PathVariable("codeArticle") String codeArticle,@PathVariable("idEntreprise") String idEntreprise){
        return articleService.findByCodeArticleAndAndIdEntreprise(codeArticle,idEntreprise);
    }

    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les articles par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les articles par Entreprise",responseContainer = "List<Article>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<Article> findAllDataByEntreprise(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0") int page){
        return articleService.findAllDataByEntreprise(idEntreprise, PageRequest.of(page, 4));
    }

    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return articleService.getNumberPages(idEntreprise,PageRequest.of(page, 4));
    }
}
