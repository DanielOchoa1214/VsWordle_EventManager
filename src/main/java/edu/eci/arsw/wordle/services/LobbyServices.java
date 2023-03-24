package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.PlayerInterface;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServices {

    @Autowired
    private PlayerInterface playerList = null;

    public void addPlayer(Player player) {
        synchronized (playerList) {
            playerList.addPlayer(player);
        }
    }

    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        return playerList.getPlayer(nickname);
    }

    public List<Player> getPlayerList() {
        return playerList.getPlayers();
    }
}
