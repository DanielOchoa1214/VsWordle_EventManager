package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PalabraServices {

    @Autowired
    private LobbiesInterface lobby;

    public boolean provePalabra(String palabra, int round, String nickname) throws PalabrasNotFoundException, PlayerNotFoundException {
        Palabra wordInt = new Palabra(palabra);
        Palabra word = lobby.getPalabra(round);
        synchronized (word) {
            if(!word.isTaken() && wordInt.equals(lobby.getPalabra(round))) {
                word.setTaken(true);
                lobby.getPlayer(nickname).addRoundWon();
                return true;
            }
        }
        return false;
    }

    public String getWord(int round) throws PalabrasNotFoundException {
        return lobby.getPalabra(round).getText();
    }

}
