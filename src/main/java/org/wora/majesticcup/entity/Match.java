package org.wora.majesticcup.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "match")
public class Match {
    @Id
    private String id;
    private int round;
    private ObjectId team1;
    private ObjectId team2;
    private MatchResult result;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchResult {
        private int team1Goals;
        private int team2Goals;
        private List<PlayerStatistic> statistics;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerStatistic {
        private ObjectId playerId;
        private int goals;
        private int assists;
        private int yellowCards;
        private int redCards;
    }

}

