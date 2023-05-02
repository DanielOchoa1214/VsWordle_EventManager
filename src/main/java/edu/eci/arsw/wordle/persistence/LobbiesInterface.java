package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface LobbiesInterface {

    Lobby getLobby(String idLobby);
    ConcurrentHashMap<String, Lobby> getLobbies();
    String addLobby(Player player);
    void resetLobby(String idLobby);
    void deleteLobby(String idLobby);
    void updateLobby(Lobby newLobby);

}
