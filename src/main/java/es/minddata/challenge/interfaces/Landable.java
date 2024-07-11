package es.minddata.challenge.interfaces;

import es.minddata.challenge.model.Planet;

public interface Landable {

    boolean land(Planet planet, Location location);

    boolean land(CosmoPort cosmoPort);
}