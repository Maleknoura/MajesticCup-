package org.wora.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;
import org.wora.majesticcup.entity.Match;
import org.wora.majesticcup.mapper.MatchMapper;
import org.wora.majesticcup.repository.MatchRepository;
import org.wora.majesticcup.service.interfaces.MatchService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    public MatchResponseDTO save(MatchRequestDTO matchRequestDTO) {
        Match match = matchMapper.toEntity(matchRequestDTO);
        Match savedMatch = matchRepository.save(match);
        return matchMapper.toResponse(savedMatch);
    }

    @Override
    public List<MatchResponseDTO> getAllMatchs() {
        return matchRepository.findAll().stream()
                .map(matchMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MatchResponseDTO getMatchById(String id) {
        Match match = matchRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        return matchMapper.toResponse(match);
    }

    @Override
    public MatchResponseDTO updateMatch(MatchRequestDTO matchRequestDTO, String id) {
        Match existingMatch = matchRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));

        matchMapper.updateMatchFromDTO(matchRequestDTO, existingMatch);

        Match savedMatch = matchRepository.save(existingMatch);
        return matchMapper.toResponse(savedMatch);
    }

    @Override
    public void deleteMatch(String id) {
        Match match = matchRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
        matchRepository.delete(match);
    }
}
