package org.wora.majesticcup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class MatchDetails {
    private String matchId;
    private String team1;
    private String team2;
    private Result result;
    private String winner;
}
