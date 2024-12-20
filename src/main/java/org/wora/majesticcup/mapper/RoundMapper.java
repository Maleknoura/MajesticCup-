package org.wora.majesticcup.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.wora.majesticcup.dto.round.RoundRequestDTO;
import org.wora.majesticcup.dto.round.RoundResponseDTO;
import org.wora.majesticcup.entity.Match;
import org.wora.majesticcup.entity.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoundMapper {
    @Mapping(target = "competitionId", expression = "java(new org.bson.types.ObjectId(dto.getCompetitionId()))")
    @Mapping(target = "matches", qualifiedByName = "mapMatchDetails")
    Round toEntity(RoundRequestDTO dto);

    @Mapping(target = "competitionId", expression = "java(entity.getCompetitionId().toString())")
    @Mapping(target = "matches", qualifiedByName = "mapMatchDetailsResponse")
    RoundResponseDTO toDto(Round entity);

    @Named("mapMatchDetails")
    default List<Round.MatchDetails> mapMatchDetails(List<RoundRequestDTO.MatchDetailsRequestDTO> matchDetails) {
        if (matchDetails == null) return new ArrayList<>();
        return matchDetails.stream().map(this::mapMatchDetail).collect(Collectors.toList());
    }

    @Named("mapMatchDetailsResponse")
    default List<RoundResponseDTO.MatchDetailsResponseDTO> mapMatchDetailsResponse(List<Round.MatchDetails> matchDetails) {
        if (matchDetails == null) return new ArrayList<>();
        return matchDetails.stream().map(this::mapMatchDetailResponse).collect(Collectors.toList());
    }

    default Round.MatchDetails mapMatchDetail(RoundRequestDTO.MatchDetailsRequestDTO dto) {
        Round.MatchDetails matchDetails = new Round.MatchDetails();
        matchDetails.setMatchId(dto.getMatchId());
        matchDetails.setTeam1(dto.getTeam1());
        matchDetails.setTeam2(dto.getTeam2());
        matchDetails.setResult(mapMatchResult(dto.getResult()));
        return matchDetails;
    }

    default RoundResponseDTO.MatchDetailsResponseDTO mapMatchDetailResponse(Round.MatchDetails matchDetails) {
        RoundResponseDTO.MatchDetailsResponseDTO dto = new RoundResponseDTO.MatchDetailsResponseDTO();
        dto.setMatchId(matchDetails.getMatchId());
        dto.setTeam1(matchDetails.getTeam1());
        dto.setTeam2(matchDetails.getTeam2());
        dto.setResult(mapMatchResultResponse(matchDetails.getResult()));
        return dto;
    }

    default Match.MatchResult mapMatchResult(RoundRequestDTO.MatchResultRequestDTO dto) {
        Match.MatchResult result = new Match.MatchResult();
        result.setTeam1Goals(dto.getTeam1Goals());
        result.setTeam2Goals(dto.getTeam2Goals());
        result.setStatistics(mapPlayerStatistics(dto.getStatistics()));
        return result;
    }

    default RoundResponseDTO.MatchResultResponseDTO mapMatchResultResponse(Match.MatchResult result) {
        RoundResponseDTO.MatchResultResponseDTO dto = new RoundResponseDTO.MatchResultResponseDTO();
        dto.setTeam1Goals(result.getTeam1Goals());
        dto.setTeam2Goals(result.getTeam2Goals());
        dto.setStatistics(mapPlayerStatisticsResponse(result.getStatistics()));
        return dto;
    }

    default List<Match.PlayerStatistic> mapPlayerStatistics(List<RoundRequestDTO.PlayerStatisticRequestDTO> statistics) {
        if (statistics == null) return new ArrayList<>();
        return statistics.stream()
                .map(stat -> {
                    Match.PlayerStatistic playerStat = new Match.PlayerStatistic();
                    playerStat.setPlayerId(new ObjectId(stat.getPlayerId()));
                    playerStat.setGoals(stat.getGoals());
                    playerStat.setAssists(stat.getAssists());
                    playerStat.setYellowCards(stat.getYellowCards());
                    playerStat.setRedCards(stat.getRedCards());
                    return playerStat;
                })
                .collect(Collectors.toList());
    }

    default List<RoundResponseDTO.PlayerStatisticResponseDTO> mapPlayerStatisticsResponse(List<Match.PlayerStatistic> statistics) {
        if (statistics == null) return new ArrayList<>();
        return statistics.stream()
                .map(stat -> {
                    RoundResponseDTO.PlayerStatisticResponseDTO dto = new RoundResponseDTO.PlayerStatisticResponseDTO();
                    dto.setPlayerId(stat.getPlayerId().toString());
                    dto.setGoals(stat.getGoals());
                    dto.setAssists(stat.getAssists());
                    dto.setYellowCards(stat.getYellowCards());
                    dto.setRedCards(stat.getRedCards());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
