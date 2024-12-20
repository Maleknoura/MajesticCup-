package org.wora.majesticcup.service.interfaces;

import org.wora.majesticcup.dto.round.RoundRequestDTO;
import org.wora.majesticcup.dto.round.RoundResponseDTO;

import java.util.List;

public interface RoundService {
    RoundResponseDTO createRound(RoundRequestDTO roundRequestDTO);
    List<RoundResponseDTO> getAllRounds();
    RoundResponseDTO getRoundById(String id);
    RoundResponseDTO updateRound(String id, RoundRequestDTO roundRequestDTO);
    void deleteRound(String id);
    List<RoundResponseDTO> getRoundsByCompetitionId(String competitionId);
}
