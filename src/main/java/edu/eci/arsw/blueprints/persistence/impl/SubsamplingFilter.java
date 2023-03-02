package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filtering;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsamplingFilter implements Filtering {
    @Override
    public Set<Blueprint> filter(Set<Blueprint> blueprints) {
        Set<Blueprint> filteredBps = new HashSet<>();
        for(Blueprint bp : blueprints){
            filteredBps.add(filter(bp));
        }
        return filteredBps;
    }

    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        for(int i = 0; i < points.size(); i++) {
            if(i % 2 == 0){
                filteredPoints.add(points.get(i));
            }
        }
        Point[] points1 = new Point[filteredPoints.size()];
        points1 = filteredPoints.toArray(points1);
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(), points1);
    }
}
