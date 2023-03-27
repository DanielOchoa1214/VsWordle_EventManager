package edu.eci.arsw.wordle.model;

import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private boolean isClosed = false;
    private final int maxPlayers;
    private final List<Player> playerList;
    private final List<Palabra> palabraList;
    private int idLobby;

    public Lobby(int id, int maxPlayers, List<Palabra> palabraList) {
        this.idLobby = id;
        this.maxPlayers = maxPlayers;
        this.playerList = new ArrayList<>();
        this.palabraList = palabraList;
    }

    private void setClosed() {
        if(playerList.size() >= maxPlayers) {
            isClosed = true;
        }
    }

    private boolean nicknameExists(Player player) {
        return playersNicknames().contains(player.getNickname());
    }

    private List<String> playersNicknames() {
        List<String> nicknames = new ArrayList<>();
        for (Player player: playerList) {
            nicknames.add(player.getNickname());
        }
        return nicknames;
    }

    public boolean addPlayer(Player player) {
        synchronized (palabraList) {
            System.out.println(!nicknameExists(player));
            if (!isClosed && !nicknameExists(player)) {
                playerList.add(player);
                setClosed();
                return true;
            }
        }
        return false;
    }

    public Palabra getPalabra(int round) throws PalabrasNotFoundException{
        try {
            return palabraList.get(round);
        } catch (Exception e) {
            throw new PalabrasNotFoundException("No se encontro la palabra");
        }
    }

    public List<Palabra> getPalabras() throws PalabrasNotFoundException {
        try {
            return palabraList;
        } catch (Exception e) {
            throw new PalabrasNotFoundException("No se encontraron las palabras");
        }
    }

    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        for(Player player: playerList) {
            if(player.getNickname().equals(nickname)) {
                return player;
            }
        }
        throw new PlayerNotFoundException("No se econcontro el jugador");
    }

    public List<Player> getPlayers() throws PlayerNotFoundException {
        if (!playerList.isEmpty()) {return playerList;}
        throw new PlayerNotFoundException("No se encontraron jugadores");
    }

    public List<String> getMissingPlayers(String host) {
        List<String> allNicknames = playersNicknames();
        allNicknames.remove(host);
        return allNicknames;
    }

    public boolean startGame() {
        if (!isClosed) {
            isClosed = true;
            return true;
        }
        //ya inicio el juego
        return false;
    }

    public String toString() {
        return "id = " + idLobby;
    }

}
