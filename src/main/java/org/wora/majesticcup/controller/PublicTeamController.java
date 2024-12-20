package org.wora.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("/api/public/teams")
@AllArgsConstructor
public class PublicTeamController {
    private final TeamService teamService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable String id) {
        TeamResponseDTO team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<TeamResponseDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }


}

