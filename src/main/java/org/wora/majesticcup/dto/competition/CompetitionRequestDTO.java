package org.wora.majesticcup.dto.competition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionRequestDTO {
    private String name;
    private int numberOfTeams;
    private List<String> teamIds;
    private int currentRound;
}
