package org.wora.majesticcup.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;
import org.wora.majesticcup.service.interfaces.MatchService;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(matchService.save(matchRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<MatchResponseDTO>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatchs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getMatchById(@PathVariable String id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> updateMatch(
            @PathVariable String id,
            @RequestBody MatchRequestDTO matchRequestDTO) {
        return ResponseEntity.ok(matchService.updateMatch(matchRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable String id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
