package org.wora.majesticcup.dto.round;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoundResponseDTO {
    private String id;
    private int roundNumber;
    private String competitionId;
    private List<MatchDetailsResponseDTO> matches;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchDetailsResponseDTO {
        private String matchId;
        private String team1;
        private String team2;
        private MatchResultResponseDTO result;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchResultResponseDTO {
        private int team1Goals;
        private int team2Goals;
        private List<PlayerStatisticResponseDTO> statistics;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerStatisticResponseDTO {
        private String playerId;
        private int goals;
        private int assists;
        private int yellowCards;
        private int redCards;
    }
}

