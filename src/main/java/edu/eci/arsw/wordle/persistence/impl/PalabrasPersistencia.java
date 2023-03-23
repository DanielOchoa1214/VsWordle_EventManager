package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.persistence.Palabras;
import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PalabrasPersistencia implements Palabras {
    private final ArrayList<String> palabras = new ArrayList<>();

    public PalabrasPersistencia() {
        //prueba
        palabras.add("nuevo");
        palabras.add("permanecer");
        palabras.add("nunca");
    }

    @Override
    public String getPalabra(int round) {
        synchronized (palabras.get(round)) {
            return palabras.get(round);
        }
    }

    @Override
    public Character getLetter(int posLetter, int round) throws PalabrasNotFoundException {
        try {
            Character lock = palabras.get(round).charAt(posLetter);
            synchronized (lock) {
                return lock;
            }
        } catch (Exception e) {
            throw new PalabrasNotFoundException("La palabra consultada no se encontro");
        }
    }

}
