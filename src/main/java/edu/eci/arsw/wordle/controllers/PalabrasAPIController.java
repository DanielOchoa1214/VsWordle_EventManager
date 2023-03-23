package edu.eci.arsw.wordle.controllers;

import edu.eci.arsw.wordle.persistence.PalabrasNotFoundException;
import edu.eci.arsw.wordle.services.PalabraServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/palabras")
public class PalabrasAPIController {

    @Autowired
    PalabraServices palabraServices = null;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> proveLetter(@RequestParam String palabra, @RequestParam int round, @RequestParam String nickname) {
        try{
            boolean data = palabraServices.provePalabra(palabra, round, nickname);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro la letra", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{round}", method = RequestMethod.GET)
    public ResponseEntity<?> getWord(@PathVariable("round")  int round) {
        try{
            String data = palabraServices.getWord(round);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("No se encontro la palabra", HttpStatus.NOT_FOUND);
        }
    }
}
