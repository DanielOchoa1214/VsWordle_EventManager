package edu.eci.arsw.wordle.model;

import java.util.List;

public class Lobby {
    private boolean isClosed = false;
    private final int maxPlayers;
    private final List<Player> playerList;
    private final List<Palabra> palabraList;
    private int idLobby;

    public Lobby(int maxPlayers, List<Player> playerList, List<Palabra> palabraList) {
        this.maxPlayers=maxPlayers;
        this.playerList=playerList;
        this.palabraList=palabraList;
    }
    public boolean addPlayer(Player player) {
        synchronized (palabraList) {
            if (!isClosed) {
                playerList.add(player);
                setClosed();
                return true;
            }
        }
        return false;
    }

    private void setClosed() {
        if(playerList.size() > maxPlayers) {
            isClosed = true;
        }
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Palabra> getPalabraList(){
        return palabraList;
    }
}
