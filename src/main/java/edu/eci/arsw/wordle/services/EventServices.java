package edu.eci.arsw.wordle.services;

import edu.eci.arsw.wordle.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.awt.image.AreaAveragingScaleFilter;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class EventServices {
    @Autowired
    private RestTemplate restTemplate;
    private final String SERVER = "http://20.96.73.221/lobbies";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public void startGame(String lobbyId){
        String url = SERVER + lobbyId + "/startGame";
        HttpEntity<?> requestUpdate = new HttpEntity<>(null);
        restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Void.class);
    }

    public List<Player> endGame(String lobbyId){
        try {
            String url = SERVER + lobbyId + "/winner";
            ResponseEntity<Player[]> response = restTemplate.getForEntity(url, Player[].class);
            Player[] players = response.getBody();
            List<Player> playerList = Arrays.asList(players);
            return playerList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removePlayer(String lobbyId, Player player) {
        try {
            String url = SERVER + lobbyId + "/players";
            HttpEntity<Player> request = new HttpEntity<>(player);
            restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCorrectLetter(String lobbyId, Player player){
        try {
            String url = SERVER + lobbyId + "/players/addCorrect";
            HttpEntity<Player> request = new HttpEntity<>(player);
            restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addWrongLetter(String lobbyId, Player player){
        try {
            String url = SERVER + lobbyId + "/players/addWrong";
            HttpEntity<Player> request = new HttpEntity<>(player);
            restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
