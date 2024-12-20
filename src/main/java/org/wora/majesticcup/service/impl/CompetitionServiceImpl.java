package org.wora.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.competition.CompetitionRequestDTO;
import org.wora.majesticcup.dto.competition.CompetitionResponseDTO;
import org.wora.majesticcup.entity.Competition;
import org.wora.majesticcup.mapper.CompetitionMapper;
import org.wora.majesticcup.repository.CompetitionRepository;
import org.wora.majesticcup.service.interfaces.CompetitionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    @Override
    public CompetitionResponseDTO createCompetition(CompetitionRequestDTO competitionRequestDTO) {
        Competition competition = competitionMapper.toEntity(competitionRequestDTO);
        Competition savedCompetition = competitionRepository.save(competition);
        return competitionMapper.toDto(savedCompetition);
    }

    @Override
    public List<CompetitionResponseDTO> getAllCompetitions() {
        return competitionRepository.findAll().stream()
                .map(competitionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompetitionResponseDTO getCompetitionById(String id) {
        Competition competition = competitionRepository.findById(String.valueOf(new ObjectId(id)))
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
        return competitionMapper.toDto(competition);
    }

    @Override
    public CompetitionResponseDTO updateCompetition(String id, CompetitionRequestDTO competitionRequestDTO) {
        Competition existingCompetition = competitionRepository.findById(String.valueOf(new ObjectId(id)))
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));

        // Mise à jour des champs
        existingCompetition.setName(competitionRequestDTO.getName());
        existingCompetition.setNumberOfTeams(competitionRequestDTO.getNumberOfTeams());
        existingCompetition.setTeamIds(competitionRequestDTO.getTeamIds().stream()
                .map(ObjectId::new)
                .collect(Collectors.toList()));
        existingCompetition.setCurrentRound(competitionRequestDTO.getCurrentRound());

        Competition savedCompetition = competitionRepository.save(existingCompetition);
        return competitionMapper.toDto(savedCompetition);
    }

    @Override
    public void deleteCompetition(String id) {
        Competition competition = competitionRepository.findById(String.valueOf(new ObjectId(id)))
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
        competitionRepository.delete(competition);
    }
}
