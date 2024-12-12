package org.wora.majesticcup.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "teams")
public class Team {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String city;

    private List<Player> players;
}
