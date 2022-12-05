package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.CategoryDTO;
import com.hassan.gestiondestock.model.category;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CategoryMapper {

    CategoryDTO toDto(category model);
    CategoryDTO toDto(Optional<category> model);
    category   toModel(CategoryDTO dto);
    List<CategoryDTO> toDtos(List<category> model);
    List<category>   toModels(List<CategoryDTO> dto);

}
