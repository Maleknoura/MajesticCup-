package org.wora.majesticcup.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "competition")
public class Competition {
    @Id
    private String id;
    private String name;
    private int numberOfTeams;
    private List<ObjectId> teamIds = new ArrayList<>();
    private int currentRound;
    private List<ObjectId> roundIds = new ArrayList<>();
}
