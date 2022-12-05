package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.adressDto;
import com.hassan.gestiondestock.model.adress;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AdressMapper {

    adressDto toDto(adress model);
    adress   toModel(adressDto dto);
    List<adressDto> toDtos(List<adress> model);
    List<adress>   toModels(List<adressDto> dto);
}
