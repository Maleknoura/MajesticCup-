package org.wora.majesticcup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.competition.CompetitionRequestDTO;
import org.wora.majesticcup.dto.competition.CompetitionResponseDTO;
import org.wora.majesticcup.service.interfaces.CompetitionService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/competitions")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionResponseDTO> createCompetition(
            @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        CompetitionResponseDTO createdCompetition = competitionService.createCompetition(competitionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompetition);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> getAllCompetitions() {
        List<CompetitionResponseDTO> competitions = competitionService.getAllCompetitions();
        return ResponseEntity.ok(competitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> getCompetitionById(@PathVariable String id) {
        CompetitionResponseDTO competition = competitionService.getCompetitionById(id);
        return ResponseEntity.ok(competition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> updateCompetition(
            @PathVariable String id,
            @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        CompetitionResponseDTO updatedCompetition = competitionService.updateCompetition(id, competitionRequestDTO);
        return ResponseEntity.ok(updatedCompetition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable String id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }
}
