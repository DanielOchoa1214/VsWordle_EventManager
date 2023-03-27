package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.model.Palabra;
import edu.eci.arsw.wordle.services.PalabraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/palabras")
public class PalabrasAPIController {

    @Autowired
    PalabraServices palabraServices = null;

    @RequestMapping(value = "/")
    public ResponseEntity<?> proveWord(@RequestParam("palabra") String palabra, @RequestParam("round")  int round, @RequestParam("nickname") String nickname) {
        try{
            boolean data = palabraServices.proveWord(palabra, round, nickname);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro la letra", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{round}")
    public ResponseEntity<?> getWord(@PathVariable("round")  int round) {
        try{
            String data = palabraServices.getWord(round);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro la palabra", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getWords() {
        try{
            List<Palabra> data = palabraServices.getWords();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro la palabra", HttpStatus.NOT_FOUND);
        }
    }
}
