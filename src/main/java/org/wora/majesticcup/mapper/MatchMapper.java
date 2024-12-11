package org.wora.majesticcup.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;
import org.wora.majesticcup.entity.Match;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    Match toEntity(MatchRequestDTO createMatchDTO);

    @Mapping(source = "result.team1Goals" , target = "result.team1Goals")
    @Mapping(source = "result.team2Goals" , target = "result.team2Goals")
    @Mapping(source = "result.statistics" , target = "result.statistics")
    MatchResponseDTO toResponse (Match match);
}
