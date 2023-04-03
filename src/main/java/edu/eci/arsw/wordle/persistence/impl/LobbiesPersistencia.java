package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LobbiesPersistencia implements LobbiesInterface {

    private ConcurrentHashMap<String, Lobby> lobbies =  new ConcurrentHashMap<>();

    public LobbiesPersistencia() {
        lobbies.put("test", new Lobby(10));
    }

    private boolean isIdLobbyNoRepeat(Lobby lobby) {
        return lobbies.containsKey((String) lobby.getIdLobby());
    }

    @Override
    public Lobby getLobby(String idLobby) {
        return lobbies.get(idLobby);
    }

    @Override
    public String addLobby(Player player) {
        Lobby lobby = new Lobby(10);
        if(!isIdLobbyNoRepeat(lobby)) {
            lobby.setHost(player);
            lobby.addPlayer(player);
            lobbies.put(lobby.getIdLobby(), lobby);
            return lobby.getIdLobby();
        } else {
            return addLobby(player);
        }
    }

    @Override
    public ConcurrentHashMap<String, Lobby> getLobbies() {
        return lobbies;
    }

    @Override
    public void resetLobby(String idLobby) {
        lobbies.get(idLobby).resetLobby();
    }

    @Override
    public void deleteLobby(String idLobby) {
        lobbies.remove(idLobby);
    }
}
