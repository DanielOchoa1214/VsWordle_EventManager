package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.persistence.PalabraInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PalabrasPersistencia implements PalabraInterface {
    private final ArrayList<Palabra> palabras = new ArrayList<>();

    public PalabrasPersistencia() {
        //prueba
        palabras.add(new Palabra("nuevo"));
        palabras.add(new Palabra("permanecer"));
        palabras.add(new Palabra("nunca"));
    }

    @Override
    public Palabra getPalabra(int round) {
        return palabras.get(round);
    }

}
