package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.Palabras;
import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServices {

    @Autowired
    private final Palabras palabras = null;
    //@Autowired
    private List<Player> playerList = null;
    public boolean proveLetter(int posLetter, int round, Character letter) throws PalabrasNotFoundException {
        return palabras.getLetter(posLetter, round) == letter;
    }

    public void proveWord(int round) {
        //preguntar implementación
        return;
    }

}
