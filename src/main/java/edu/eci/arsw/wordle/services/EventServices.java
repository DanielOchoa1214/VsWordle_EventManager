package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EventServices {
    @Autowired
    private RestTemplate restTemplate;
    private final String SERVER = "http://localhost:8080/";

    public void startGame(String lobbyId){
        String url = SERVER + "lobbies/" + lobbyId + "/startGame";
        System.out.println(restTemplate.getForObject(url, Boolean.class));
    }

    public List<Player> endGame(String lobbyId){
        String url = SERVER + "lobbies/" + lobbyId + "/endGame";
        List<Player> nose = restTemplate.getForObject(url, List.class);
        System.out.println(nose);
        return nose;
    }

    public void removePlayer(String lobbyId, Player player){

    }

    public void addWrongLetter(String idLobby, Player player){

    }

    public void addCorrectLetter(String idLobby, Player player){

    }


}
