package org.wora.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.round.RoundRequestDTO;
import org.wora.majesticcup.dto.round.RoundResponseDTO;
import org.wora.majesticcup.entity.Round;
import org.wora.majesticcup.mapper.RoundMapper;
import org.wora.majesticcup.repository.RoundRepository;
import org.wora.majesticcup.service.interfaces.RoundService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;
    private final RoundMapper roundMapper;

    @Override
    public RoundResponseDTO createRound(RoundRequestDTO roundRequestDTO) {
        Round round = roundMapper.toEntity(roundRequestDTO);
        Round savedRound = roundRepository.save(round);
        return roundMapper.toDto(savedRound);
    }

    @Override
    public List<RoundResponseDTO> getAllRounds() {
        return roundRepository.findAll().stream()
                .map(roundMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoundResponseDTO getRoundById(String id) {
        Round round = roundRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Round not found with id: " + id));
        return roundMapper.toDto(round);
    }

    @Override
    public RoundResponseDTO updateRound(String id, RoundRequestDTO roundRequestDTO) {
        Round existingRound = roundRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Round not found with id: " + id));

        // Mettre à jour les champs
        existingRound.setRoundNumber(roundRequestDTO.getRoundNumber());
        existingRound.setCompetitionId(new ObjectId(roundRequestDTO.getCompetitionId()));
        existingRound.setMatches(roundMapper.mapMatchDetails(roundRequestDTO.getMatches()));

        Round savedRound = roundRepository.save(existingRound);
        return roundMapper.toDto(savedRound);
    }

    @Override
    public void deleteRound(String id) {
        Round round = roundRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Round not found with id: " + id));
        roundRepository.delete(round);
    }

    @Override
    public List<RoundResponseDTO> getRoundsByCompetitionId(String competitionId) {
        List<Round> rounds = roundRepository.findByCompetitionId(new ObjectId(competitionId));
        return rounds.stream()
                .map(roundMapper::toDto)
                .collect(Collectors.toList());
    }
}
