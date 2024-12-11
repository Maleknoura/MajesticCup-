package org.wora.majesticcup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wora.majesticcup.entity.Match;

public interface MatchRepository extends MongoRepository<Match, Long> {
}
