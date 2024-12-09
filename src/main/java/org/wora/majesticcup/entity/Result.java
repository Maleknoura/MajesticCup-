package org.wora.majesticcup.entity;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {
    private int team1Goals;
    private int team2Goals;
    private List<Statistics> statistics;
}

