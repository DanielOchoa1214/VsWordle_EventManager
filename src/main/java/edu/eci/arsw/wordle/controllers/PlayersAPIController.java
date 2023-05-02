package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Lobby;
import edu.eci.arsw.wordle.model.Player;
import edu.eci.arsw.wordle.persistence.exceptions.LobbyException;
import edu.eci.arsw.wordle.persistence.exceptions.PlayerException;
import edu.eci.arsw.wordle.services.LobbyServices;
import edu.eci.arsw.wordle.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/lobbies/{idLobby}/players")
public class PlayersAPIController {

    @Autowired
    PlayerServices playerServices = null;
    @Autowired
    LobbyServices lobbyServices = null;

    @GetMapping()
    public ResponseEntity<?> getPlayers(@PathVariable("idLobby") String idLobby) {
        try {
            Lobby lobby = lobbyServices.getLobby(idLobby);
            List<Player> data = playerServices.getPlayerList(lobby);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PlayerException | LobbyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{nickname}")
    public ResponseEntity<?> getPlayer(@PathVariable String nickname, @PathVariable("idLobby") String idLobby) {
        try{
            Lobby lobby = lobbyServices.getLobby(idLobby);
            Player data = playerServices.getPlayer(nickname, lobby);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (PlayerException | LobbyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player, @PathVariable("idLobby") String idLobby) {
        try{
            Lobby lobby = lobbyServices.getLobby(idLobby);
            boolean success = playerServices.addPlayer(player, lobby);
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (LobbyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/missing")
    public ResponseEntity<?> missingPlayers(@RequestParam String host, @PathVariable("idLobby") String idLobby) {
        try{
            Lobby lobby = lobbyServices.getLobby(idLobby);
            List<String> missingPlayers = playerServices.getMissingPlayers(host, lobby);
            return new ResponseEntity<>(missingPlayers, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Ocurrio algo, lo sentimos", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> removePlayer(@RequestBody Player player, @PathVariable("idLobby") String idLobby){
        try{
            Lobby lobby = lobbyServices.getLobby(idLobby);
            playerServices.removePlayer(player, lobby);
            return new ResponseEntity<>(true, HttpStatus.FOUND);
        } catch (PlayerException | LobbyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
