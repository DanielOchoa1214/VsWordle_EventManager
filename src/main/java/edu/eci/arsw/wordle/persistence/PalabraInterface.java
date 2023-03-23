package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Palabra;

public interface PalabraInterface {

    Palabra getPalabra(int round)  throws PalabrasNotFoundException;
}
