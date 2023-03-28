package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;


import java.util.List;

public interface LobbiesInterface {

    Lobby getLobby(int idLobby);
    void addLobby();
    List<Lobby> getLobbies();
    void resetLobby(int idLobby);

}
