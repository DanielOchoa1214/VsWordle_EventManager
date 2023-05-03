package edu.eci.arsw.wordle.persistence.mongointerface;

import edu.eci.arsw.wordle.model.Lobby;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoDataBaseInterface extends MongoRepository<Lobby, String> {
    @Query("{id: '?0'}")
    Lobby getLobby(String id);
}
