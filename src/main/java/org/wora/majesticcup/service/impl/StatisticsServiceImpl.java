package org.wora.majesticcup.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.statistic.PlayerCardsDTO;
import org.wora.majesticcup.dto.statistic.TopAssistDTO;
import org.wora.majesticcup.dto.statistic.TopScorerDTO;
import org.wora.majesticcup.service.interfaces.StatisticsService;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {
    private MongoTemplate mongoTemplate;

    public List<TopScorerDTO> getTopScorers() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("result").exists(true)
                        .and("result.statistics").exists(true)),
                unwind("$result.statistics"),
                group("$result.statistics.playerId")
                        .sum("$result.statistics.goals").as("goals"),
                match(Criteria.where("goals").gt(0)),
                lookup("players", "_id", "_id", "playerInfo"),
                unwind("playerInfo"),
                project()
                        .and("_id").as("playerId")
                        .and("goals").as("goals")
                        .and("playerInfo.name").as("playerName")
                        .and("playerInfo.surname").as("playerSurname"),
                sort(Sort.Direction.DESC, "goals")
        );

        AggregationResults<TopScorerDTO> results = mongoTemplate.aggregate(
                aggregation, "matches", TopScorerDTO.class
        );
        log.info("Found {} top scorers", results.getMappedResults().size());
        return results.getMappedResults();
    }

    public List<TopAssistDTO> getTopAssists() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("result").exists(true)
                        .and("result.statistics").exists(true)),
                unwind("$result.statistics"),
                group("$result.statistics.playerId")
                        .sum("$result.statistics.assists").as("assists"),
                match(Criteria.where("assists").gt(0)),
                lookup("players", "_id", "_id", "playerInfo"),
                unwind("playerInfo"),
                project()
                        .and("_id").as("playerId")
                        .and("assists").as("assists")
                        .and("playerInfo.name").as("playerName")
                        .and("playerInfo.surname").as("playerSurname"),
                sort(Sort.Direction.DESC, "assists")
        );

        AggregationResults<TopAssistDTO> results = mongoTemplate.aggregate(
                aggregation, "matches", TopAssistDTO.class
        );
        log.info("Found {} top assists", results.getMappedResults().size());
        return results.getMappedResults();
    }

    public List<PlayerCardsDTO> getCards() {
        Aggregation aggregation = Aggregation.newAggregation(
                match(Criteria.where("result").exists(true)
                        .and("result.statistics").exists(true)),
                unwind("$result.statistics"),
                group("$result.statistics.playerId")
                        .sum("$result.statistics.yellowCards").as("yellowCards")
                        .sum("$result.statistics.redCards").as("redCards"),
                match(Criteria.where("yellowCards").gt(0)
                        .orOperator(Criteria.where("redCards").gt(0))),
                lookup("players", "_id", "_id", "playerInfo"),
                unwind("playerInfo"),
                project()
                        .and("_id").as("playerId")
                        .and("yellowCards").as("yellowCards")
                        .and("redCards").as("redCards")
                        .and("playerInfo.name").as("playerName")
                        .and("playerInfo.surname").as("playerSurname")
                        .andExpression("yellowCards + redCards").as("totalCards"),
                sort(Sort.Direction.DESC, "redCards", "yellowCards")
        );

        AggregationResults<PlayerCardsDTO> results = mongoTemplate.aggregate(
                aggregation, "matches", PlayerCardsDTO.class
        );
        log.info("Found {} players with cards", results.getMappedResults().size());
        return results.getMappedResults();
    }
}