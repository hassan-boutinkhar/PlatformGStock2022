package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.ArticleDto;
import com.hassan.gestiondestock.model.Article;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {
        CategoryMapper.class
})
public interface ArticleMapper {

    Article toModel(ArticleDto dto);


    ArticleDto   toDto(Optional<Article> model);
    ArticleDto   toDto(Article model);
    List<ArticleDto> toDtos(List<Article> model);
    List<Article>   toModels(List<ArticleDto> dto);
}
