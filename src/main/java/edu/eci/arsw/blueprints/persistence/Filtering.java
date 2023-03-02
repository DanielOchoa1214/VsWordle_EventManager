package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

public interface Filtering {
    Set<Blueprint> filter(Set<Blueprint> blueprints);
    Blueprint filter(Blueprint blueprint);
}
