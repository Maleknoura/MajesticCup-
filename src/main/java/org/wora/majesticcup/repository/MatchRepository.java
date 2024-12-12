package org.wora.majesticcup.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.wora.majesticcup.entity.Match;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, Long> {
    Optional<Match> findById(ObjectId id);
}
