package org.wora.majesticcup.service.interfaces;

import org.wora.majesticcup.dto.competition.CompetitionRequestDTO;
import org.wora.majesticcup.dto.competition.CompetitionResponseDTO;

import java.util.List;

public interface CompetitionService {
    CompetitionResponseDTO createCompetition(CompetitionRequestDTO competitionRequestDTO);
    List<CompetitionResponseDTO> getAllCompetitions();
    CompetitionResponseDTO getCompetitionById(String id);
    CompetitionResponseDTO updateCompetition(String id, CompetitionRequestDTO competitionRequestDTO);
    void deleteCompetition(String id);
}
