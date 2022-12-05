package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.InformationDto;
import com.hassan.gestiondestock.model.Informations;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface InformationMapper {

    InformationDto toDto(Informations model);
    Informations   toModel(InformationDto dto);
    List<InformationDto> toDtos(List<Informations> model);
    List<Informations>   toModels(List<InformationDto> dto);
}
