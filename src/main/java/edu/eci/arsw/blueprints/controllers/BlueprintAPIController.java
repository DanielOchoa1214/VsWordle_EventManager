/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bpService = null;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBlueprints(){
        Set<Blueprint> data = bpService.getAllBlueprints();
        return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{author}")
    public ResponseEntity<?> getBlueprintsByAuthor(@PathVariable String author){
        try{
            Set<Blueprint> data = bpService.getBlueprintsByAuthor(author);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
            // return new ResponseEntity<>("El autor no existe :'(", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{author}/{bpName}")
    public ResponseEntity<?> getBlueprint(@PathVariable String author, @PathVariable String bpName){
        try{
            Blueprint data = bpService.getBlueprint(author, bpName);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e){
            throw new ResourceNotFoundException(e.getMessage());
            // return new ResponseEntity<>("El plano especificado no existe... F", HttpStatus.NOT_FOUND);
        }
    }

    // POSTS
    @PostMapping
    public ResponseEntity<?> createBlueprint(@RequestBody Blueprint bp){
        try{
            bpService.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PutMapping(value="/{author}/{bpName}")
    public ResponseEntity<?> updateBlueprint(@PathVariable String author,@PathVariable String bpName, @RequestBody Blueprint bp ) {
        try{
            bpService.updateBlueprint(author, bpName, bp);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}

