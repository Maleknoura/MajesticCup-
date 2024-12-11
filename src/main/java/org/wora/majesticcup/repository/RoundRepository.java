package org.wora.majesticcup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wora.majesticcup.entity.Round;

public interface RoundRepository extends MongoRepository<Round, Long> {
}
