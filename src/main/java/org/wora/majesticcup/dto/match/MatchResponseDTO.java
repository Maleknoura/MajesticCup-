package org.wora.majesticcup.dto.match;

import java.util.List;

public record MatchResponseDTO(
        String id,
        int round,
        String team1,
        String team2,
        ResponseMatchResultDTO result,
        String winner
) {

    public record ResponseMatchResultDTO(
            int team1Goals,
            int team2Goals,
            List<ResponsePlayerStatisticDTO> statistics
    ) {}

    public record ResponsePlayerStatisticDTO(
            String playerId,
            int goals,
            int assists,
            int yellowCards,
            int redCards
    ) {}
}

