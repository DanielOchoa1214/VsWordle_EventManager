package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class WebSocketHandler extends StompSessionHandlerAdapter {

    @Autowired
    SimpMessagingTemplate msgt;
    @Autowired
    private EventServices eventServices;

    @MessageMapping("/startGame.{idLobby}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void handleStartEvent(@DestinationVariable String idLobby) throws Exception {
        eventServices.startGame(idLobby);
    }

    @MessageMapping("/endGame.{idLobby}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void handleEndEvent(@DestinationVariable String idLobby) throws Exception {
        List<Player> sortPlayers = eventServices.endGame(idLobby);
        msgt.convertAndSend("/topic/endGame." + idLobby, sortPlayers);
    }

    @MessageMapping("/removePlayer.{idLobby}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void handleRemovePlayerEvent(Player player, @DestinationVariable String idLobby) throws Exception {
        eventServices.removePlayer(idLobby, player);
        msgt.convertAndSend("/topic/removePlayer." + idLobby, player);
    }

    @MessageMapping("/wrongLetter.{idLobby}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void handleWrongLetterEvent(Player player, @DestinationVariable String idLobby) throws Exception {
        eventServices.addWrongLetter(idLobby, player);
    }

    @MessageMapping("/correctLetter.{idLobby}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void handleCorrectLetterEvent(Player player, @DestinationVariable String idLobby) throws Exception {
        eventServices.addCorrectLetter(idLobby, player);
    }
}