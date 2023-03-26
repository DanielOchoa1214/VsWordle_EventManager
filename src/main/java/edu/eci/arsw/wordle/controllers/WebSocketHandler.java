package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Player;
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


    @MessageMapping("/joinGame")
    public void handlePointEvent(Player player) throws Exception {
        System.out.println("Nuevo punto recibido en el servidor!:"+player);
        msgt.convertAndSend("/topic/joinGame", player);
    }
}