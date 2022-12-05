/*
package com.hassan.gestiondestock.Mapper;

import com.hassan.gestiondestock.dto.CommandeClientsDto;
import com.hassan.gestiondestock.model.CommandeClient;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {
         LigneCommandeClientsMapper.class
})
public interface CommandeClientsMapper {

    CommandeClientsDto toDto(CommandeClient model);
    CommandeClientsDto toDto(Optional<CommandeClient> model);
    CommandeClient   toModel(CommandeClientsDto dto);
    List<CommandeClientsDto> toDtos(List<CommandeClient> model);
    List<CommandeClient>   toModels(List<CommandeClientsDto> dto);
}
*/
