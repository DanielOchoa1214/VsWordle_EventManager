package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Player;
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
        } catch (Exception e){
            return new ResponseEntity<>("No se encontraron jugadores", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/players/{nickname}")
    public ResponseEntity<?> getPlayer(@PathVariable String nickname) {
        try{
            Player data = lobbyServices.getPlayer(nickname);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro el jugador", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        try{
            boolean success = lobbyServices.addPlayer(player);
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("El jugador no se pudo añadir", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/players/missing")
    public ResponseEntity<?> missingPlayers(@RequestParam String host) {
        try{
            List<String> missingPlayers = lobbyServices.getMissingPlayers(host);
            return new ResponseEntity<>(missingPlayers, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("La petición no se pudo procesar", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/startGame")
    public ResponseEntity<?> missingPlayers() {
        try{
            Boolean success = lobbyServices.startGame();
            return new ResponseEntity<>(success, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("La petición no se pudo procesar", HttpStatus.NOT_FOUND);
        }
    }
}
