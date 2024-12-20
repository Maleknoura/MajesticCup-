package org.wora.majesticcup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.round.RoundRequestDTO;
import org.wora.majesticcup.dto.round.RoundResponseDTO;
import org.wora.majesticcup.service.interfaces.RoundService;

import java.util.List;

@RestController
@RequestMapping("/api/rounds")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @GetMapping
    public ResponseEntity<List<RoundResponseDTO>> getAllRounds() {
        List<RoundResponseDTO> rounds = roundService.getAllRounds();
        return ResponseEntity.ok(rounds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoundResponseDTO> getRoundById(@PathVariable String id) {
        RoundResponseDTO round = roundService.getRoundById(id);
        return ResponseEntity.ok(round);
    }

    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<RoundResponseDTO>> getRoundsByCompetitionId(
            @PathVariable String competitionId) {
        List<RoundResponseDTO> rounds = roundService.getRoundsByCompetitionId(competitionId);
        return ResponseEntity.ok(rounds);
    }


}
