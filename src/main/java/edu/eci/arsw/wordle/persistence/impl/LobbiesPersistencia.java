package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class LobbiesPersistencia implements LobbiesInterface {

    private static final String ARCHIVO_PALABRAS = "wordlist_spanish_fil.txt";
    private List<Palabra> wordList = null;
    private List<Lobby> lobbies =  new ArrayList<>();

    public LobbiesPersistencia() {
        getWordList();
        lobbies.add(new Lobby(lobbies.size(), 5, getLobbyWords(10)));
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
    public Lobby getLobby(int idLobby) {
        return lobbies.get(idLobby);
    }

    @Override
    public void addLobby() {
        //Posterior implementación
    }

    @Override
    public List<Lobby> getLobbies() {
        //posterior implementación
        return null;
    }

}
