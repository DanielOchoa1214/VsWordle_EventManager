package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import edu.eci.arsw.wordle.persistence.LobbyException;
import edu.eci.arsw.wordle.persistence.PlayerException;
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

    public Lobby getLobby(int idLobby) throws LobbyException {
        try {
            return lobbies.getLobby(idLobby);
        } catch (IndexOutOfBoundsException e) {
            throw new LobbyException(LobbyException.LOBBY_NOT_FOUND);
        }
    }

    public boolean addPlayer(Player player) throws LobbyException {
        if(lobbies.getLobby(0).nicknameExists(player)) throw new LobbyException(LobbyException.PLAYER_EXISTS);
        if(!lobbies.getLobby(0).addPlayer(player)) throw new LobbyException(LobbyException.IS_CLOSED);
        return lobbies.getLobby(0).addPlayer(player);
    }

    public Player getPlayer(String nickname) throws PlayerException {
        if(lobbies.getLobby(0).getPlayer(nickname) == null) throw new PlayerException(PlayerException.NOT_FOUND_PLAYER);
        return lobbies.getLobby(0).getPlayer(nickname);
    }

    public List<Player> getPlayerList() throws PlayerException {
        if(lobbies.getLobby(0).getPlayers().isEmpty())throw new PlayerException(PlayerException.NOT_FOUND_PLAYER);
        return lobbies.getLobby(0).getPlayers();
    }

    public List<String> getMissingPlayers(String host) {
        return lobbies.getLobby(0).getMissingPlayers(host);
    }

    public boolean startGame() throws LobbyException{
        if (!lobbies.getLobby(0).startGame()) throw new LobbyException(LobbyException.IS_CLOSED);
        return lobbies.getLobby(0).startGame();
    }

    public Player getLobbyWinner() throws LobbyException {
        if(lobbies.getLobby(0).getIsFinished().get()) throw new LobbyException(LobbyException.IS_NOT_FINISHED);
        Player playerWinner = lobbies.getLobby(0).lobbyWinner();
        lobbies.resetLobby(0);
        return playerWinner;
    }
}
