package org.wora.majesticcup.service.interfaces;

import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;

import java.util.List;

public interface  MatchService {

    MatchResponseDTO save(MatchRequestDTO matchRequestDTO);
   List<MatchResponseDTO> getAllMatchs();
   MatchResponseDTO getMatchById(String id);
    MatchResponseDTO updateMatch(MatchRequestDTO matchRequestDTO , String id);
    void deleteMatch(String id);

}
