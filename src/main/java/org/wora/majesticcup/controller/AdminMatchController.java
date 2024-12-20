package org.wora.majesticcup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;
import org.wora.majesticcup.service.interfaces.MatchService;

@RestController
@RequestMapping("/api/admin/matches")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminMatchController {

    private final MatchService matchService;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchRequestDTO matchRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(matchService.save(matchRequestDTO));
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
