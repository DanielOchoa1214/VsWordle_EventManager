/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.Filtering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{
    private final Map<Tuple<String,String>,Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("Ibai", "LaVeladaDelAño",pts);

        Point[] pts1 = new Point[]{new Point(0, 0),new Point(0, 0), new Point(1, 1), new Point(0, 0)};
        Blueprint bp1=new Blueprint("johncito", "thepaint",pts1);
        Blueprint bp2=new Blueprint("johncito", "thepaintV2",pts1);
        Blueprint bp3=new Blueprint("john", "thepaint",pts1);

        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.putIfAbsent(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }



    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint bp = blueprints.get(new Tuple<>(author, bprintname));
        if(bp == null){throw new BlueprintNotFoundException("The Blueprint was not found :'(");}
        return bp;
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> foundBps = new HashSet<>();
        blueprints.forEach((key, value) -> {
            if(value.getAuthor().equals(author)){
                foundBps.add(value);
            }
        });
        if(foundBps.isEmpty()) throw new BlueprintNotFoundException("There's no blueprints with the given author D:");
        return foundBps;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        return new HashSet<>(blueprints.values());
    }

    @Override
    public void updateBlueprint(String author, String name, Blueprint newBp) throws BlueprintNotFoundException {
        if(!blueprints.containsKey(new Tuple<>(author, name))) throw new BlueprintNotFoundException("No existe el plano especificado D:");
        blueprints.put(new Tuple<>(author, name), newBp);
    }
}
