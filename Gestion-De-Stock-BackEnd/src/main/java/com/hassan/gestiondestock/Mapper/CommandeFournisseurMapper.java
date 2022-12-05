/*
package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.commandeFournisseurDto;
import com.hassan.gestiondestock.model.commandeFournisseurs;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {
        LigneCommandeClientsMapper.class
})
public interface CommandeFournisseurMapper {

    commandeFournisseurDto toDto(commandeFournisseurs model);
    commandeFournisseurDto toDto(Optional<commandeFournisseurs> model);
    commandeFournisseurs   toModel(commandeFournisseurDto dto);
    List<commandeFournisseurDto> toDtos(List<commandeFournisseurs> model);
    List<commandeFournisseurs>   toModels(List<commandeFournisseurDto> dto);
}
*/
