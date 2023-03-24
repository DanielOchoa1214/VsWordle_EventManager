package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import edu.eci.arsw.wordle.persistence.PlayerInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlayersPersistencia implements PlayerInterface {

    private List<Player> playerList = new ArrayList<>();
    public PlayersPersistencia() {
        playerList.add(new Player("Yo"));
        playerList.add(new Player("Juliana"));
        playerList.add(new Player("DANO"));
    }

    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        for(Player player: playerList) {
            if(player.getNickname().equals(nickname)) {
                return player;
            }
        }
        throw new PlayerNotFoundException("No se econcontro el player");
    }
    public List<Player> getPlayers() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
}
