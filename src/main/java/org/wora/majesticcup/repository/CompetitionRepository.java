package org.wora.majesticcup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wora.majesticcup.entity.Competition;

public interface CompetitionRepository extends MongoRepository<Competition,String> {
}
