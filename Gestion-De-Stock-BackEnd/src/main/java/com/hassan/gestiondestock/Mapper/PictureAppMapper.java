package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.PictureAppDTO;
import com.hassan.gestiondestock.model.picturesApp;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PictureAppMapper {

    picturesApp toModel(PictureAppDTO dto);
    PictureAppDTO   toDto(Optional<picturesApp> model);
    PictureAppDTO   toDto(picturesApp model);
    List<PictureAppDTO> toDtos(List<picturesApp> model);
    List<picturesApp>   toModels(List<PictureAppDTO> dto);
}
