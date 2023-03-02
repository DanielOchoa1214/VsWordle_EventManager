/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>("El autor no existe :'(", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{author}/{bpName}")
    public ResponseEntity<?> getBlueprint(@PathVariable String author, @PathVariable String bpName){
        try{
            Blueprint data = bpService.getBlueprint(author, bpName);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e){
            return new ResponseEntity<>("El plano especificado no existe... F", HttpStatus.NOT_FOUND);
        }
    }
}

