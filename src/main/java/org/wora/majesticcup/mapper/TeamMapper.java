package org.wora.majesticcup.mapper;

import org.wora.majesticcup.dto.player.PlayerDTO;
import org.wora.majesticcup.dto.team.TeamRequestDTO;
import org.wora.majesticcup.dto.team.TeamResponseDTO;
import org.wora.majesticcup.entity.Player;
import org.wora.majesticcup.entity.Team;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class})
public interface TeamMapper {
    PlayerMapper PLAYER_MAPPER = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "players", qualifiedByName = "mapPlayersList")
    Team toEntity(TeamRequestDTO dto);

    @Mapping(target = "players", qualifiedByName = "mapPlayersListToDto")
    TeamResponseDTO toDto(Team entity);

    @Named("mapPlayersList")
    default List<Player> mapPlayersList(List<PlayerDTO> playerDTOs) {
        if (playerDTOs == null) {
            return null;
        }
        return playerDTOs.stream()
                .map(PLAYER_MAPPER::toEntity)
                .collect(Collectors.toList());
    }

    @Named("mapPlayersListToDto")
    default List<PlayerDTO> mapPlayersListToDto(List<Player> players) {
        if (players == null) {
            return null;
        }
        return players.stream()
                .map(PLAYER_MAPPER::toDto)
                .collect(Collectors.toList());
    }
}
