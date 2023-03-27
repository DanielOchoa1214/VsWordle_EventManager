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
    private LobbiesInterface lobbies;

    public boolean newLobby (Player host, int maxPlayers) {
        //Posterior implementación
        return false;
    }

    public boolean addPlayer(Player player) {
        return lobbies.getLobby(0).addPlayer(player);
    }

    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        return lobbies.getLobby(0).getPlayer(nickname);
    }

    public List<Player> getPlayerList() throws PlayerNotFoundException {
        return lobbies.getLobby(0).getPlayers();
    }

    public List<String> getMissingPlayers(String host) {
        return lobbies.getLobby(0).getMissingPlayers(host);
    }

    public boolean startGame() {
        return lobbies.getLobby(0).startGame();
    }

}
