package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.Mapper.MvtMapper;
import com.hassan.gestiondestock.Repository.MvtDeStockRepository;
import com.hassan.gestiondestock.dto.mvtDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class MvtService {

    @Autowired
    private MvtDeStockRepository mvtDeStockRepository;
    @Autowired
    private MvtMapper mvtMapper;
    @Autowired
    private ArticleService articleService;

    public mvtDto save(mvtDto mvtDTO) {
        //System.out.println("hani");
        return mvtMapper.toDto(mvtDeStockRepository.save(mvtMapper.toModel(mvtDTO)));
    }
    public List<mvtDto> getAllData(String IdEntreprise){
        return mvtMapper.toDtos(mvtDeStockRepository.findAllByIdEntreprise(IdEntreprise));
    }

    public mvtDto findLastMvt(String idEntreprise, Long idArticle) {
        List<mvtDto> mvtDtoList=mvtMapper.toDtos(mvtDeStockRepository.findAllByIdEntrepriseAndArticleIdOrderByCreationDateDesc(idEntreprise,idArticle));
        if(mvtDtoList.size()!=0){
            return mvtDtoList.get(0);
        }else{
            mvtDto mvtDtoT=new mvtDto();
            mvtDtoT.setQuantite(articleService.findByCodeArticleAndAndIdEntreprise(idEntreprise,idEntreprise).getQuantite());
            return mvtDtoT;
        }
    }

    public List<mvtDto> findArtcileMvts(String idEntreprise, Long idArticle) {
        return mvtMapper.toDtos(mvtDeStockRepository.findAllByIdEntrepriseAndArticleIdOrderByCreationDateDesc(idEntreprise,idArticle));
    }
    public boolean mvtIsEmpty(String idEntreprise, Long idArticle){

        List<mvtDto> mvtDtoList=mvtMapper.toDtos(mvtDeStockRepository.findAllByIdEntrepriseAndArticleIdOrderByCreationDateDesc(idEntreprise,idArticle));
        return mvtDtoList.size() == 0;
    }
}
