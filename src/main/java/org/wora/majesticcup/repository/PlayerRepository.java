package org.wora.majesticcup.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wora.majesticcup.entity.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

}
