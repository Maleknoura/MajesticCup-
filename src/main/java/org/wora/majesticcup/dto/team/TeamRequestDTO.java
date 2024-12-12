package org.wora.majesticcup.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wora.majesticcup.dto.player.PlayerDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDTO {
    private String name;
    private String city;
    private List<PlayerDTO> players;
}

