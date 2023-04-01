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
public class PlayerServices {

    @Autowired
    private LobbiesInterface lobbies;

    public boolean addPlayer(Player player, Lobby lobby) throws LobbyException {
        if(lobby.nicknameExists(player)) throw new LobbyException(LobbyException.PLAYER_EXISTS);
        if(lobby.getIsClosed().get()) throw new LobbyException(LobbyException.IS_CLOSED);
        if(player.getNickname().equals("")) throw new LobbyException(LobbyException.EMPTY_NICK);
        return lobby.addPlayer(player);
    }

    public void removePlayer(Player player, Lobby lobby) throws LobbyException, PlayerException {
        if(!lobby.nicknameExists(player)) throw new PlayerException(PlayerException.NOT_FOUND_PLAYER);
        if(player.getNickname().equals("")) throw new LobbyException(LobbyException.EMPTY_NICK);
        try {
            lobby.removePlayer(player);
        } catch (IndexOutOfBoundsException e) {
            lobbies.deleteLobby(lobby.getIdLobby());
        }
    }

    public Player getPlayer(String nickname, Lobby lobby) throws PlayerException {
        if(lobby.getPlayer(nickname) == null) throw new PlayerException(PlayerException.NOT_FOUND_PLAYER);
        return lobby.getPlayer(nickname);
    }

    public List<Player> getPlayerList(Lobby lobby) throws PlayerException {
        if(lobby.getPlayers().isEmpty())throw new PlayerException(PlayerException.NOT_FOUND_PLAYER);
        return lobby.getPlayers();
    }

    public List<String> getMissingPlayers(String host, Lobby lobby) {
        return lobby.getMissingPlayers(host);
    }
}
