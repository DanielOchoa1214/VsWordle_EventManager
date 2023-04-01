package edu.eci.arsw.wordle.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Lobby {
    private static final String ARCHIVO_PALABRAS = "wordlist_spanish_fil.txt";
    private static List<Palabra> wordList = null;
    private AtomicBoolean isClosed = new AtomicBoolean(false);
    private AtomicBoolean isFinished = new AtomicBoolean(false);
    private List<Player> playerList;
    private List<Palabra> palabraList;
    private int idLobby;

    public Lobby(int id, int maxRounds) {
        setWordList();
        this.idLobby = id;
        this.playerList = new ArrayList<>();
        this.palabraList = lobbyWords(maxRounds);
    }

    private static void setWordList() {
        if(wordList == null) {
            wordList = new ArrayList<>();
            BufferedReader br = null;
            try {
                File file = new File(ARCHIVO_PALABRAS);
                br = new BufferedReader(new FileReader(file));

                String palabra;
                while((palabra = br.readLine()) != null) {
                    wordList.add(new Palabra(palabra));
                }
                br.close();
            } catch (IOException e ) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Collections.shuffle(wordList, new Random(10000));
        }
    }

    private List<Palabra> lobbyWords(int rounds) {
        List<Palabra> lobbyWords = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i<rounds; i++) {
            int wordIndex = r.nextInt(wordList.size());
            lobbyWords.add(wordList.get(wordIndex));
        }
        return lobbyWords;
    }

    private void setClosed() {
        if(playerList.size() >= 5) {
            isClosed.set(true);
        }
    }

    public boolean nicknameExists(Player player) {
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
            if(!isClosed.get()) {
                playerList.add(player);
                setClosed();
                return true;
            }
        }
        return false;
    }

    public void removePlayer(Player player){
        synchronized (playerList){
            playerList.remove(player);
        }
        if(playerList.isEmpty()){
            System.out.println("HOLI");
            resetLobby();
        }
    }

    public Palabra getPalabra(int round) {
        return palabraList.get(round);
    }

    public List<Palabra> getPalabras() {
        return palabraList;
    }

    public Player getPlayer(String nickname) {
        for(Player player: playerList) {
            if(player.getNickname().equals(nickname)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public List<String> getMissingPlayers(String host) {
        List<String> allNicknames = playersNicknames();
        allNicknames.remove(host);
        return allNicknames;
    }

    public boolean startGame() {
        if (!isClosed.get()) {
            isClosed.set(true);
            return isClosed.get();
        }
        return isClosed.get();
    }

    public Player lobbyWinner() {
        Player playerWinner = new Player();
        //no haya empezado la partida
        for (Player player: playerList) {
            if (player.getRoundsWon() > playerWinner.getRoundsWon()){
                playerWinner = player;
            }
        }
        return playerWinner;
    }
    //deletaplayer
    //rest comprobar ganador de la sala, el player, get..., concurrencia

    public String toString() {
        return "id = " + idLobby;
    }

    public int getIdLobby() {
        return idLobby;
    }

    public void resetLobby() {
        isClosed.set(false);
        isFinished.set(false);
        palabraList = lobbyWords(10);
        playerList = new ArrayList<>();
    }
    public AtomicBoolean getIsFinished() {
        return isFinished;
    }

    public AtomicBoolean getIsClosed() {
        return isClosed;
    }
}
