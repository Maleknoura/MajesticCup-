package org.wora.majesticcup.mapper;


import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;
import org.wora.majesticcup.entity.Match;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {ObjectId.class})
public interface MatchMapper {
    @Mapping(source = "team1", target = "team1", qualifiedByName = "stringToObjectId")
    @Mapping(source = "team2", target = "team2", qualifiedByName = "stringToObjectId")
    @Mapping(target = "result.statistics", qualifiedByName = "mapPlayerStatistics")
    Match toEntity(MatchRequestDTO matchRequestDTO);

    @Mapping(source = "team1", target = "team1", qualifiedByName = "objectIdToString")
    @Mapping(source = "team2", target = "team2", qualifiedByName = "objectIdToString")
    @Mapping(target = "result.statistics", qualifiedByName = "mapResponsePlayerStatistics")
    MatchResponseDTO toResponse(Match match);

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return id != null ? new ObjectId(id) : null;
    }

    @Named("objectIdToString")
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toString() : null;
    }

    @Named("mapPlayerStatistics")
    default List<Match.PlayerStatistic> mapPlayerStatistics(List<MatchRequestDTO.PlayerStatisticRequestDTO> dtoStatistics) {
        if (dtoStatistics == null) {
            return null;
        }
        return dtoStatistics.stream()
                .map(this::mapPlayerStatistic)
                .collect(Collectors.toList());
    }

    @Named("mapResponsePlayerStatistics")
    default List<MatchResponseDTO.ResponsePlayerStatisticDTO> mapResponsePlayerStatistics(List<Match.PlayerStatistic> statistics) {
        if (statistics == null) {
            return null;
        }
        return statistics.stream()
                .map(this::mapResponsePlayerStatistic)
                .collect(Collectors.toList());
    }

    default Match.PlayerStatistic mapPlayerStatistic(MatchRequestDTO.PlayerStatisticRequestDTO dto) {
        Match.PlayerStatistic statistic = new Match.PlayerStatistic();
        statistic.setPlayerId(new ObjectId(dto.playerId()));
        statistic.setGoals(dto.goals());
        statistic.setAssists(dto.assists());
        statistic.setYellowCards(dto.yellowCards());
        statistic.setRedCards(dto.redCards());
        return statistic;
    }

    default MatchResponseDTO.ResponsePlayerStatisticDTO mapResponsePlayerStatistic(Match.PlayerStatistic statistic) {
        return new MatchResponseDTO.ResponsePlayerStatisticDTO(
                statistic.getPlayerId().toString(),
                statistic.getGoals(),
                statistic.getAssists(),
                statistic.getYellowCards(),
                statistic.getRedCards()
        );
    }
}