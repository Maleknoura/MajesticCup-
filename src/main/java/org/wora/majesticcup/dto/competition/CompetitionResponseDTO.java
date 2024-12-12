package org.wora.majesticcup.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionResponseDTO {
    private String id;
    private String name;
    private int numberOfTeams;
    private List<String> teamIds;
    private int currentRound;
    private List<String> roundIds;
}
