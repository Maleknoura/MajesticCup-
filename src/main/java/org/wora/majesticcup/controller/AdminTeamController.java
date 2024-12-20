package org.wora.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.team.TeamRequestDTO;
import org.wora.majesticcup.dto.team.TeamResponseDTO;
import org.wora.majesticcup.service.impl.TeamService;

@RestController
@RequestMapping("/api/admin/teams")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminTeamController {
    private final TeamService teamService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO createdTeam = teamService.createTeam(teamRequestDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeamResponseDTO> updateTeam(
            @PathVariable String id,
            @Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO updatedTeam = teamService.updateTeam(id, teamRequestDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
