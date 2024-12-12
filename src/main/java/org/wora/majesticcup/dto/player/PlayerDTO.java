package org.wora.majesticcup.dto.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String id;
    private String name;
    private String surname;
    private String position;
    private int number;
}
