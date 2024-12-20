package org.wora.majesticcup.service.interfaces;

import org.wora.majesticcup.dto.statistic.PlayerCardsDTO;
import org.wora.majesticcup.dto.statistic.TopAssistDTO;
import org.wora.majesticcup.dto.statistic.TopScorerDTO;

import java.util.List;

public interface StatisticsService {

    List<TopScorerDTO> getTopScorers();
    List<TopAssistDTO> getTopAssists();
    List<PlayerCardsDTO> getCards();
}
