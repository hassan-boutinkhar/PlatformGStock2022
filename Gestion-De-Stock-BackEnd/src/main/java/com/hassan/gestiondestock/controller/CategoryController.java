package com.hassan.gestiondestock.controller;

import com.hassan.gestiondestock.Repository.CategoryRepository;
import com.hassan.gestiondestock.dto.CategoryDTO;
import com.hassan.gestiondestock.model.category;
import com.hassan.gestiondestock.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@Api("/gestionDeStock/v1/api/category/")//dire que cest un api
@RestController
@RequestMapping("/gestionDeStock/v1/api/category/")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(value = "addCategory")
    @ApiOperation(value = "Ajouter un categorie ou le modifier" , notes = "Cette methode permet de creer un noveau categorie ou le modifier",response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ajouter ou modifier avec succes")
    })
    public CategoryDTO save(@RequestBody CategoryDTO categorydto){
        System.out.print("The addition of 1");
        return  categoryService.save(categorydto);
    }

    @GetMapping(value ="id/{id}" )
    @ApiOperation(value = "Rechercher un categorie par son ID" , notes = "Cette methode permet de rechercher un methode par son ID",response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'categorie a été trouvé"),
            @ApiResponse(code = 404, message = "L'categorie n'a été pas trouvé")
    })
    public CategoryDTO findById(@PathVariable("id") Long id){

        return categoryService.findById(id);
    }

    @GetMapping(value = "codeCategory/{codeCategory}")
    @ApiOperation(value = "Rechercher un categorie par son code" , notes = "Cette methode permet de rechercher un methode par son code",response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'categorie a été trouvé"),
            @ApiResponse(code = 404, message = "L'categorie n'a été pas trouvé")
    })
    public CategoryDTO findByCodeArticle(@PathVariable("codeCategory") String codeCategory){

        return categoryService.findByCodeCategory(codeCategory);
    }
    @GetMapping(value = "all")
    @ApiOperation(value = "Retourner la liste de tout les categories " , notes = "Cette methode permet de retourner la liste de tout les categories",responseContainer = "List<CategoryDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<CategoryDTO> findAllData(){
        return categoryService.findAllData();
    }
    @GetMapping(value = "getByCodeCategoryAndIdE/{codeCategory}/{idEntreprise}")
    @ApiOperation(value = "Retourner category unique " , notes = "Cette methode permet de retourner category",response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public CategoryDTO findByCodeCtegoryAndAndIdEntreprise(@PathVariable("codeCategory") String codeCategory,@PathVariable("idEntreprise") String idEntreprise){
        return categoryService.findByCodeCtegoryAndAndIdEntreprise(codeCategory,idEntreprise);
    }
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Supprimer un categorie par son ID" , notes = "Cette methode permet de supprimer un categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'categorie a été supprimer avec succs"),
    })
    public void deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
    }

    @GetMapping(value = "allByIdEntreprise/{idEntreprise}")
    @ApiOperation(value = "Retourner la liste de tout les categories par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les categories par Entreprise",responseContainer = "List<CategoryDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public List<CategoryDTO> findAllDataByEntreprise(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0") int page){
        return categoryService.findAllDataByEntreprise(idEntreprise,PageRequest.of(page, 4));
    }
    @PutMapping(value = "updateCategory")
    @ApiOperation(value = "Mise a jour ou bien modifier un category" , notes = "Cette methode permet de modifier un category",response = CategoryDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes "),
    })
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categorydto) {
        return  categoryService.save(categorydto);
    }

    @GetMapping(value = "pagination")
    @ApiOperation(value = "Retourner la liste de tout les categories par Entreprise" , notes = "Cette methode permet de retourner la liste de tout les categories par Entreprise",responseContainer = "List<CategoryDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation avec succes ou liste vide"),
    })
    public Page<category> findAllDataByEntrepriseAndPagination(@RequestParam(defaultValue = "0") int page){
        return categoryRepository.findAll(PageRequest.of(page, 4));
    }
    @GetMapping(value = "getNumberpage/{idEntreprise}")
    public int getNumberPages(@PathVariable String idEntreprise,@RequestParam(defaultValue = "0",required = false) int page){
        return categoryService.getNumberPages(idEntreprise,PageRequest.of(page, 4));
    }
    }
