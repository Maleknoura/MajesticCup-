package org.wora.majesticcup.entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Player {
    private String id;
    private String name;
    private String surname;
    private String position;
    private int number;
}
