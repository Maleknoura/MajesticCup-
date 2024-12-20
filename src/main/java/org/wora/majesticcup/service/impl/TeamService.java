package org.wora.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.team.TeamRequestDTO;
import org.wora.majesticcup.dto.team.TeamResponseDTO;
import org.wora.majesticcup.entity.Team;
import org.wora.majesticcup.mapper.TeamMapper;
import org.wora.majesticcup.repository.TeamRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) {
        Team team = teamMapper.toEntity(teamRequestDTO);
        if (team.getPlayers() != null) {
            team.getPlayers().forEach(player -> {
                if (player.getId() == null || player.getId().isEmpty()) {
                    player.setId(new ObjectId().toString());
                }
            });
        }
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toDto(savedTeam);
    }

    public TeamResponseDTO getTeamById(String id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        return teamMapper.toDto(team);
    }

    public List<TeamResponseDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    public TeamResponseDTO updateTeam(String id, TeamRequestDTO teamRequestDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        Team updatedTeam = teamMapper.toEntity(teamRequestDTO);
        updatedTeam.setId(id);
        Team savedTeam = teamRepository.save(updatedTeam);

        return teamMapper.toDto(savedTeam);
    }

    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }
}
