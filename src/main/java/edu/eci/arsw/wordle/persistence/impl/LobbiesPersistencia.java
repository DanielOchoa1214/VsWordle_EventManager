package edu.eci.arsw.wordle.persistence.impl;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import edu.eci.arsw.wordle.persistence.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
public class LobbiesPersistencia implements LobbiesInterface {

    private static final String ARCHIVO_PALABRAS = "wordlist_spanish_fil.txt";

    private Lobby lobby;

    public LobbiesPersistencia() {
        lobby = new Lobby(5,
                new ArrayList<Player>(), crearPalabras());
    }

    //pruebas
    private List<Palabra> crearPalabras() {
        List<Palabra> palabras = new ArrayList<>();
        try {
            File file = new File(ARCHIVO_PALABRAS);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String palabra = scanner.nextLine();
                Palabra p = new Palabra(palabra);
                palabras.add(p);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Mezclar aleatoriamente la lista de palabras
        Collections.shuffle(palabras);

        return palabras;
    }

    @Override
    public Lobby getLobby() {
        return lobby;
    }

    @Override
    public Palabra getPalabra(int round) {
        return lobby.getPalabraList().get(round);
    }

    @Override
    public Player getPlayer(String nickname) throws PlayerNotFoundException {
        for(Player player: lobby.getPlayerList()) {
            if(player.getNickname().equals(nickname)) {
                return player;
            }
        }
        throw new PlayerNotFoundException("No se econcontro el player");
    }
    @Override
    public List<Player> getPlayers() {
        return lobby.getPlayerList();
    }

    @Override
    public void addPlayer(Player player) {
        lobby.addPlayer(player);
    }

    public List<String> getMissingPlayers(String host) {
        List<String> allNicknames = playersNicknames();
        allNicknames.remove(host);
        return allNicknames;
    }

    private List<String> playersNicknames() {
        List<String> nicknames = new ArrayList<>();
        for (Player player: lobby.getPlayerList()) {
            nicknames.add(player.getNickname());
        }
        return nicknames;
    }
}
