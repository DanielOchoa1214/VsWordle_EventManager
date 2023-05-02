package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;

import java.util.concurrent.ConcurrentHashMap;

// @Service
public class PersistenciaLocal implements LobbiesInterface {

    private ConcurrentHashMap<String, Lobby> lobbies =  new ConcurrentHashMap<>();

    public PersistenciaLocal() {
        lobbies.put("test", new Lobby());
    }

    private boolean isIdRepeated(Lobby lobby) {
        return lobbies.containsKey(lobby.getId());
    }

    @Override
    public Lobby getLobby(String idLobby) {
        return lobbies.get(idLobby);
    }

    @Override
    public String addLobby(Player player) {
        Lobby lobby = new Lobby();
        if(!isIdRepeated(lobby)) {
            lobby.setHost(player);
            lobby.addPlayer(player);
            lobbies.put(lobby.getId(), lobby);
            return lobby.getId();
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

    @Override
    public void updateLobby(Lobby newLobby) {
        lobbies.put(newLobby.getId(), newLobby);
    }
}
