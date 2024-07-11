package es.minddata.challenge.entity;

import es.minddata.challenge.interfaces.StarShipFactory;
import org.springframework.stereotype.Component;

@Component
public class StarShipFabric implements StarShipFactory {

    @Override
    public CargoVessel createCargoVessel(String name, int maxSpeed, int weight, int size, int shieldStrength, Engine engine, int maxCapacity, int actualLoad) {
        CargoVessel cargoVessel = new CargoVessel();
        cargoVessel.setName(name);
        cargoVessel.setMaxSpeed(maxSpeed);
        cargoVessel.setWeight(weight);
        cargoVessel.setSize(size);
        cargoVessel.setShieldStrength(shieldStrength);
        cargoVessel.setEngine(engine);
        cargoVessel.setMaxCapacity(maxCapacity);
        cargoVessel.setActualLoad(actualLoad);
        return cargoVessel;
    }

    @Override
    public WarShip createWarShip(String name, int maxSpeed, int weight, int size, int shieldStrength, Engine engine, int maxGunPower) {
        WarShip warShip = new WarShip();
        warShip.setName(name);
        warShip.setMaxSpeed(maxSpeed);
        warShip.setWeight(weight);
        warShip.setSize(size);
        warShip.setShieldStrength(shieldStrength);
        warShip.setEngine(engine);
        warShip.setMaxGunPower(maxGunPower);
        return warShip;
    }

    @Override
    public ArtifactShip createArtifactShip(String name, int maxSpeed, int weight, int size, int shieldStrength, Engine engine, int maxCapacity, int actualLoad, int maxGunPower) {
        ArtifactShip artifactShip = new ArtifactShip();
        artifactShip.setName(name);
        artifactShip.setMaxSpeed(maxSpeed);
        artifactShip.setWeight(weight);
        artifactShip.setSize(size);
        artifactShip.setShieldStrength(shieldStrength);
        artifactShip.setEngine(engine);
        artifactShip.setMaxCapacity(maxCapacity);
        artifactShip.setActualLoad(actualLoad);
        artifactShip.setMaxGunPower(maxGunPower);
        return artifactShip;
    }
}