package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.LobbyException;
import edu.eci.arsw.wordle.persistence.PlayerException;
import edu.eci.arsw.wordle.services.LobbyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lobbies")
public class LobbyAPIController {

    @Autowired
    LobbyServices lobbyServices = null;

    @GetMapping(value = "/players")
    public ResponseEntity<?> getPlayers() {
        try{
            List<Player> data = lobbyServices.getPlayerList();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PlayerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/players/{nickname}")
    public ResponseEntity<?> getPlayer(@PathVariable String nickname) {
        try{
            Player data = lobbyServices.getPlayer(nickname);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PlayerException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        try{
            boolean success = lobbyServices.addPlayer(player);
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (LobbyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/players/missing")
    public ResponseEntity<?> missingPlayers(@RequestParam String host) {
        try{
            List<String> missingPlayers = lobbyServices.getMissingPlayers(host);
            return new ResponseEntity<>(missingPlayers, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>("Ocurrio algo, lo sentimos", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/startGame")
    public ResponseEntity<?> startGame() {
        try{
            Boolean success = lobbyServices.startGame();
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (LobbyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{idLobby}")
    public ResponseEntity<?> getLobby(@PathVariable("idLobby") int idLobby) {
        try{
            Lobby lobby = lobbyServices.getLobby(idLobby);
            return new ResponseEntity<>(lobby, HttpStatus.FOUND);
        } catch (LobbyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/winner")
    public ResponseEntity<?> getLobbyWinner() {
        try{
            Player player = lobbyServices.getLobbyWinner();
            return new ResponseEntity<>(player, HttpStatus.FOUND);
        } catch (LobbyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
