package org.wora.majesticcup.dto.match;

import java.util.List;

public record MatchRequestDTO(
        int round,
        String team1,
        String team2,
        MatchResultRequestDTO result,
        String winner
) {

    public record MatchResultRequestDTO(
            int team1Goals,
            int team2Goals,
            List<PlayerStatisticRequestDTO> statistics
    ) {}

    public record PlayerStatisticRequestDTO(
            String playerId,
            int goals,
            int assists,
            int yellowCards,
            int redCards
    ) {}
}

