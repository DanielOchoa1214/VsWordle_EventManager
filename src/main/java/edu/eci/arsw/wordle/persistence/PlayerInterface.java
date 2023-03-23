package edu.eci.arsw.wordle.persistence;

import edu.eci.arsw.wordle.model.Player;

public interface PlayerInterface {
    public Player getPlayer(String nickname) throws PlayerNotFoundException;
}
