package org.wora.majesticcup.dto.statistic;

import lombok.Data;

@Data
public class PlayerCardsDTO {
    private String playerId;
    private int yellowCards;
    private int redCards;
    private int totalCards;
}
