package org.wora.majesticcup.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.wora.majesticcup.dto.round.RoundRequestDTO;
import org.wora.majesticcup.dto.round.RoundResponseDTO;
import org.wora.majesticcup.service.interfaces.RoundService;

@RestController
@RequestMapping("/api/admin/rounds")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminRoundController {
    private final RoundService roundService;
    @PostMapping
    public ResponseEntity<RoundResponseDTO> createRound(
            @RequestBody RoundRequestDTO roundRequestDTO) {
        RoundResponseDTO createdRound = roundService.createRound(roundRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRound);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoundResponseDTO> updateRound(
            @PathVariable String id,
            @RequestBody RoundRequestDTO roundRequestDTO) {
        RoundResponseDTO updatedRound = roundService.updateRound(id, roundRequestDTO);
        return ResponseEntity.ok(updatedRound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRound(@PathVariable String id) {
        roundService.deleteRound(id);
        return ResponseEntity.noContent().build();
    }

}
