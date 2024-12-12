package org.wora.majesticcup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.wora.majesticcup.entity.Competition;

import java.util.Optional;

public interface CompetitionRepository extends MongoRepository<Competition,String> {
    Optional<Competition> findByName(String name);

}
