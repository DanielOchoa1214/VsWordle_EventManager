package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LobbiesPersistencia implements LobbiesInterface {

    private List<Lobby> lobbies =  new ArrayList<>();

    public LobbiesPersistencia() {
        lobbies.add(new Lobby(lobbies.size(), 10));
    }

    @Override
    public Lobby getLobby(int idLobby) {
        return lobbies.get(idLobby);
    }

    @Override
    public void addLobby() {
        //Posterior implementación
    }

    @Override
    public List<Lobby> getLobbies() {
        //posterior implementación
        return null;
    }

    @Override
    public void resetLobby(int idLobby) {
        lobbies.get(idLobby).resetLobby();
    }

}
