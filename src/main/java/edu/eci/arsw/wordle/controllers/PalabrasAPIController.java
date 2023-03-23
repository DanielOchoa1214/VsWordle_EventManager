package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import edu.eci.arsw.wordle.services.PalabraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/palabras")
public class PalabrasAPIController {

    @Autowired
    PalabraServices palabraServices = null;

    @RequestMapping()
    public ResponseEntity<?> proveLetter(@RequestParam Character letter, @RequestParam int posLetter, @RequestParam int round, @RequestParam String player, @RequestParam boolean previousMistake) {
        try{
            boolean data = palabraServices.proveLetter(posLetter, round, letter);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro la letra", HttpStatus.NOT_FOUND);
        }
    }
}
