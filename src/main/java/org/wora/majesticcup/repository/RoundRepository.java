package org.wora.majesticcup.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.wora.majesticcup.entity.Round;

import java.util.List;
import java.util.Optional;

public interface RoundRepository extends MongoRepository<Round, Long> {
    List<Round> findByCompetitionId(ObjectId competitionId);

   Optional<Round> findById(ObjectId objectId);
}
