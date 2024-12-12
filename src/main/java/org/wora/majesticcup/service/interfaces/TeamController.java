package org.wora.majesticcup.service.interfaces;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.team.TeamRequestDTO;
import org.wora.majesticcup.dto.team.TeamResponseDTO;
import org.wora.majesticcup.service.impl.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO createdTeam = teamService.createTeam(teamRequestDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable String id) {
        TeamResponseDTO team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<TeamResponseDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> updateTeam(
            @PathVariable String id,
            @Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO updatedTeam = teamService.updateTeam(id, teamRequestDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}

