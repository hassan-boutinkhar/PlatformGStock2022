package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.mvtDto;
import com.hassan.gestiondestock.model.MvtDeStocks;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MvtMapper {

    mvtDto toDto(MvtDeStocks model);
    MvtDeStocks   toModel(mvtDto dto);
    List<mvtDto> toDtos(List<MvtDeStocks> model);
    List<MvtDeStocks>   toModels(List<mvtDto> dto);

}
