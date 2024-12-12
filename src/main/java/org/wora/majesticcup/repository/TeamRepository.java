package org.wora.majesticcup.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wora.majesticcup.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {
    Optional<Team> findByName(String name);
    List<Team> findAll();

}
