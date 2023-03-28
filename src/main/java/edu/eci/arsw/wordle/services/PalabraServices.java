package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServices {

    @Autowired
    private LobbiesInterface lobbies;

    public boolean proveWord(String palabra, int round, String nickname) throws PalabrasException {
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
        //retornar la siguiente palabra
    }

    public String getWord(int round) throws PalabrasException {
        try {
            return lobbies.getLobby(0).getPalabra(round).getText();
        } catch (IndexOutOfBoundsException e) {
            throw new PalabrasException(PalabrasException.NOT_FOUND_PALABRA);
        }
    }

    public List<Palabra> getWords() {
        return lobbies.getLobby(0).getPalabras();
    }

}
