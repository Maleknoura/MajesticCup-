package org.wora.majesticcup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wora.majesticcup.entity.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {
}
