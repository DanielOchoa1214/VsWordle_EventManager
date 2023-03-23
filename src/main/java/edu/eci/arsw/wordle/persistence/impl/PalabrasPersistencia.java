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
        palabras.add(new Palabra("cagastes"));
        palabras.add(new Palabra("nuevos"));
        palabras.add(new Palabra("permanecer"));
        palabras.add(new Palabra("textos"));
        palabras.add(new Palabra("compañeros"));
        palabras.add(new Palabra("adicional"));
        palabras.add(new Palabra("chupeteo"));
        palabras.add(new Palabra("loquito"));
        palabras.add(new Palabra("madera"));
        palabras.add(new Palabra("maderista"));
    }

    @Override
    public Palabra getPalabra(int round) {
        return palabras.get(round);
    }

}
