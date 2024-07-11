package es.minddata.challenge.interfaces;

import es.minddata.challenge.entity.ArtifactShip;
import es.minddata.challenge.entity.CargoVessel;
import es.minddata.challenge.entity.Engine;
import es.minddata.challenge.entity.WarShip;

public interface StarShipFactory {
    CargoVessel createCargoVessel(String name, int maxSpeed, int weight, int size, int shieldStrength, Engine engine, int maxCapacity, int actualLoad);

    WarShip createWarShip(String name, int maxSpeed, int weight, int size, int shieldStrength, Engine engine, int maxGunPower);

    ArtifactShip createArtifactShip(String name, int maxSpeed, int weight, int size, int shieldStrength, Engine engine, int maxCapacity, int actualLoad, int maxGunPower);
}