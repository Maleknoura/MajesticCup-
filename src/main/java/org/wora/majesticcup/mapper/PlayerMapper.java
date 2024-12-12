package org.wora.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.majesticcup.dto.player.PlayerDTO;
import org.wora.majesticcup.entity.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "number", source = "number")
    Player toEntity(PlayerDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "position", source = "position")
    @Mapping(target = "number", source = "number")
    PlayerDTO toDto(Player entity);
}
