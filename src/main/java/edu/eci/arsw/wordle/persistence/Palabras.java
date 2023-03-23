package edu.eci.arsw.wordle.persistence;

public interface Palabras {

    public String getPalabra(int round)  throws PalabrasNotFoundException;
    public Character getLetter(int posLetter, int round) throws PalabrasNotFoundException;
}
