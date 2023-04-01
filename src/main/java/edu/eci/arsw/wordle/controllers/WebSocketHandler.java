package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbiesInterface;
import edu.eci.arsw.wordle.persistence.LobbyException;
import edu.eci.arsw.wordle.services.LobbyServices;
import edu.eci.arsw.wordle.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Controller
public class WebSocketHandler extends StompSessionHandlerAdapter {

    @Autowired
    SimpMessagingTemplate msgt;

    @Autowired
    private LobbyServices lobbyServices;
    @Autowired
    private PlayerServices playerServices;

    @MessageMapping("/startGame.{idLobby}")
    public void handleStartEvent(@DestinationVariable String idLobby) throws Exception {
        lobbyServices.startGame(lobbyServices.getLobby(idLobby));
    }

    @MessageMapping("/endGame.{idLobby}")
    public void handleEndEvent(@DestinationVariable String idLobby) throws Exception {
        Player player = lobbyServices.getLobbyWinner(lobbyServices.getLobby(idLobby));
        msgt.convertAndSend("/topic/endGame", player);
    }

    @MessageMapping("/removePlayer.{idLobby}")
    public void handleRemovePlayerEvent(Player player, @DestinationVariable String idLobby) throws Exception {
        playerServices.removePlayer(player, lobbyServices.getLobby(idLobby));
        msgt.convertAndSend("/topic/removePlayer", player);
    }
}