package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServices {

    @Autowired
    private LobbiesInterface lobbies;

    public boolean proveWord(String palabra, int round, String nickname) throws PalabrasNotFoundException, PlayerNotFoundException {
        Palabra wordInt = new Palabra(palabra);
        Palabra word = lobbies.getLobby(0).getPalabra(round);
        synchronized (word) {
            if(!word.isTaken() && wordInt.equals(lobbies.getLobby(0).getPalabra(round))) {
                word.setTaken(true);
                lobbies.getLobby(0).getPlayer(nickname).addRoundWon();
                return true;
            }
        }
        return false;
    }

    public String getWord(int round) throws PalabrasNotFoundException {
        return lobbies.getLobby(0).getPalabra(round).getText();
    }

    public List<Palabra> getWords() throws PalabrasNotFoundException {
        return lobbies.getLobby(0).getPalabras();
    }

}
