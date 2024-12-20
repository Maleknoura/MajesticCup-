package org.wora.majesticcup.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wora.majesticcup.dto.statistic.PlayerCardsDTO;
import org.wora.majesticcup.dto.statistic.TopAssistDTO;
import org.wora.majesticcup.dto.statistic.TopScorerDTO;
import org.wora.majesticcup.service.impl.StatisticsServiceImpl;
import org.wora.majesticcup.service.interfaces.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api/public/statistics")
@AllArgsConstructor
@Slf4j
public class StatisticsController {


    private StatisticsServiceImpl statisticsService;

    @GetMapping("/top-scorers")
    public ResponseEntity<List<TopScorerDTO>> getTopScorers() {
        try {
            List<TopScorerDTO> topScorers = statisticsService.getTopScorers();
            return ResponseEntity.ok(topScorers);
        } catch (Exception e) {
            log.error("Error fetching top scorers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/top-assists")
    public ResponseEntity<List<TopAssistDTO>> getTopAssists() {
        try {
            List<TopAssistDTO> topAssists = statisticsService.getTopAssists();
            return ResponseEntity.ok(topAssists);
        } catch (Exception e) {
            log.error("Error fetching top assists", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cards")
    public ResponseEntity<List<PlayerCardsDTO>> getCards() {

            List<PlayerCardsDTO> cards = statisticsService.getCards();
            return ResponseEntity.ok(cards);

    }
}