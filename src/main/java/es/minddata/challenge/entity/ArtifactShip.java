package es.minddata.challenge.entity;

import es.minddata.challenge.model.Cargo;
import es.minddata.challenge.model.Planet;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArtifactShip extends StarShip {

    private int maxGunPower;

    private int maxCapacity;

    private int actualLoad;

    public void load(Cargo cargo) {
        actualLoad = actualLoad + cargo.getWeight();
    }

    public void unload(Cargo cargo) {
        actualLoad = actualLoad - cargo.getWeight();
    }

    public boolean fire(Planet planet) {
        if (!"DeathStar".equals(super.getName())) {
            return false;
        } else {
            maxGunPower = Integer.MAX_VALUE;
            planet = null;
            return true;
        }
    }
}
