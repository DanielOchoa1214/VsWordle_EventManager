package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.Players;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayersPersistencia implements Players {

    private List<Player> playerList = new ArrayList<>();

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
}
