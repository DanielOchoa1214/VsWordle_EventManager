package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Lobby;


import java.util.List;

public interface LobbiesInterface {

    Lobby getLobby(int id);

    void addLobby();

    List<Lobby> getLobbies();
}
