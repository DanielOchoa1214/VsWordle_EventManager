package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LobbiesPersistencia implements LobbiesInterface {

    private Lobby lobby;

    public LobbiesPersistencia() {
        lobby = new Lobby(5,
                crearJugadores(), crearPalabras());
    }

    //pruebas
    private List<Palabra> crearPalabras() {
        List<Palabra> palabras = new ArrayList<>();
        //prueba
        palabras.add(new Palabra("cagastes"));
        palabras.add(new Palabra("nuevos"));
        palabras.add(new Palabra("permanecer"));
        palabras.add(new Palabra("textos"));
        palabras.add(new Palabra("compañeros"));
        palabras.add(new Palabra("adicional"));
        palabras.add(new Palabra("chupeteo"));
        palabras.add(new Palabra("loquito"));
        palabras.add(new Palabra("madera"));
        palabras.add(new Palabra("maderista"));
        return palabras;
    }

    //pruebas
    public List<Player> crearJugadores() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Yo"));
        players.add(new Player("Juliana"));
        players.add(new Player("DANO"));
        return players;
    }

    @Override
    public Lobby getLobby() {
        return lobby;
    }

    @Override
    public Palabra getPalabra(int round) {
        return lobby.getPalabraList().get(round);
    }

    @Override
    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        for(Player player: lobby.getPlayerList()) {
            if(player.getNickname().equals(nickname)) {
                return player;
            }
        }
        throw new PlayerNotFoundException("No se econcontro el player");
    }
    @Override
    public List<Player> getPlayers() {
        return lobby.getPlayerList();
    }

    @Override
    public void addPlayer(Player player) {
        lobby.addPlayer(player);
    }

    public List<String> getMissingPlayers(String host) {
        List<String> allNicknames = playersNicknames();
        allNicknames.remove(host);
        return allNicknames;
    }

    private List<String> playersNicknames() {
        List<String> nicknames = new ArrayList<>();
        for (Player player: lobby.getPlayerList()) {
            nicknames.add(player.getNickname());
        }
        return nicknames;
    }
}
