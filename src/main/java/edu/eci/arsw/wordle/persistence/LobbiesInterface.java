package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;

import java.util.List;

public interface LobbiesInterface {

    Lobby getLobby();
    Palabra getPalabra(int round)  throws PalabrasNotFoundException;
    List<Palabra> getPalabras() throws PalabrasNotFoundException;
    Player getPlayer(String nickname) throws PlayerNotFoundException;
    List<Player> getPlayers();
    void addPlayer(Player player);
    List<String> getMissingPlayers(String host);

}
