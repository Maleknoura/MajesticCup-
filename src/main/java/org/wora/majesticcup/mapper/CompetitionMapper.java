package org.wora.majesticcup.mapper;



import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.majesticcup.dto.competition.CompetitionRequestDTO;
import org.wora.majesticcup.dto.competition.CompetitionResponseDTO;
import org.wora.majesticcup.entity.Competition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    @Mapping(target = "teamIds", expression = "java(convertTeamIdsToObjectIds(dto.getTeamIds()))")
    Competition toEntity(CompetitionRequestDTO dto);

    @Mapping(target = "teamIds", expression = "java(convertObjectIdsToStrings(entity.getTeamIds()))")
    CompetitionResponseDTO toDto(Competition entity);

    default List<ObjectId> convertTeamIdsToObjectIds(List<String> teamIds) {
        return teamIds == null ? new ArrayList<>() : teamIds.stream()
                .map(ObjectId::new)
                .collect(Collectors.toList());
    }

    default List<String> convertObjectIdsToStrings(List<ObjectId> objectIds) {
        return objectIds == null ? new ArrayList<>() : objectIds.stream()
                .map(ObjectId::toString)
                .collect(Collectors.toList());
    }
}