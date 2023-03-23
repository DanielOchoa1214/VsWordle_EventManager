package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.PalabraInterface;
import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import edu.eci.arsw.wordle.persistence.PlayerInterface;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PalabraServices {

    @Autowired
    private final PalabraInterface palabras = null;
    @Autowired
    private PlayerInterface playerList = null;

    public boolean provePalabra(String palabra, int round, String nickname) throws PalabrasNotFoundException, PlayerNotFoundException {
        Palabra wordInt = new Palabra(palabra);
        Palabra word = palabras.getPalabra(round);
        synchronized (word) {
            if(!word.isTaken() && wordInt.equals(palabras.getPalabra(round))) {
                word.setTaken(true);
                playerList.getPlayer(nickname).addRoundWon();
                return true;
            }
        }
        return false;
    }

    public String getWord(int round) throws PalabrasNotFoundException {
        return palabras.getPalabra(round).getText();
    }
}
