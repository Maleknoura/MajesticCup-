package org.wora.majesticcup.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wora.majesticcup.dto.match.MatchRequestDTO;
import org.wora.majesticcup.dto.match.MatchResponseDTO;

public interface  MatchService {

    MatchResponseDTO save(MatchRequestDTO matchRequestDTO);
    Page<MatchResponseDTO> getAllMatchs(Pageable pageable);
   MatchResponseDTO getMatchById(String id);
    MatchResponseDTO updateMatch(MatchRequestDTO matchRequestDTO , String id);
    boolean deleteMatch(String id);

}
