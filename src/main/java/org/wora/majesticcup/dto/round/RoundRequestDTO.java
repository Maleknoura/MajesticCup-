package org.wora.majesticcup.dto.round;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wora.majesticcup.dto.match.MatchRequestDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoundRequestDTO {
    private int roundNumber;
    private String competitionId;
    private List<MatchDetailsRequestDTO> matches;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchDetailsRequestDTO {
        private String matchId;
        private String team1;
        private String team2;
        private MatchResultRequestDTO result;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchResultRequestDTO {
        private int team1Goals;
        private int team2Goals;
        private List<PlayerStatisticRequestDTO> statistics;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerStatisticRequestDTO {
        private String playerId;
        private int goals;
        private int assists;
        private int yellowCards;
        private int redCards;
    }
}