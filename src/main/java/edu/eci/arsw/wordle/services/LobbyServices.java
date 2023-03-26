package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServices {

    @Autowired
    private LobbiesInterface lobby = null;

    public boolean newLobby (Player host, int maxPlayers) {
        return false;
    }

    public boolean addPlayer(Player player) {
        return lobby.getLobby().addPlayer(player);
    }

    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        return lobby.getPlayer(nickname);
    }

    public List<Player> getPlayerList() {
        return lobby.getPlayers();
    }

    public List<String> getMissingPlayers(String host) {
        return lobby.getMissingPlayers(host);
    }
}
