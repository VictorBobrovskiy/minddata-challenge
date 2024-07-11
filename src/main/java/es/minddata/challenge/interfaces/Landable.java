package es.minddata.challenge.interfaces;

import es.minddata.challenge.model.CosmoPort;
import es.minddata.challenge.model.Location;
import es.minddata.challenge.model.Planet;

public interface Landable {

    boolean land(Planet planet, Location location);

    boolean land(CosmoPort cosmoPort);
}