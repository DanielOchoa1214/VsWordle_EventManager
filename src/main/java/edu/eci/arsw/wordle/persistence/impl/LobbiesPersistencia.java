package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class LobbiesPersistencia implements LobbiesInterface {

    private static final String ARCHIVO_PALABRAS = "wordlist_spanish_fil.txt";
    private List<Palabra> wordList = null;
    private Lobby lobby;

    public LobbiesPersistencia() {
        getWordList();
        lobby = new Lobby(1, 5, getLobbyWords(10));
    }

    private void getWordList() {
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

    private List<Palabra> getLobbyWords(int rounds) {
        List<Palabra> lobbyWords = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i<rounds; i++) {
            int wordIndex = r.nextInt(wordList.size());
            lobbyWords.add(wordList.get(wordIndex));
        }
        return lobbyWords;
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
    public List<Palabra> getPalabras() throws PalabrasNotFoundException {
        return lobby.getPalabraList();
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


    @Override
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
