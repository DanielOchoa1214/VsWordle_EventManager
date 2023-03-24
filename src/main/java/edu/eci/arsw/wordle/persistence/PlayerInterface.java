package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Player;

import java.util.List;

public interface PlayerInterface {
    Player getPlayer(String nickname) throws PlayerNotFoundException;

    List<Player> getPlayers();
    void addPlayer(Player player);
}
