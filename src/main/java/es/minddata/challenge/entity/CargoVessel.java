package es.minddata.challenge.entity;

import es.minddata.challenge.model.Cargo;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class CargoVessel extends StarShip {

    private final int maxCapacity;

    private int actualLoad;

    public void load(Cargo cargo) {
        actualLoad = actualLoad + cargo.getWeight();
    }

    public void unload(Cargo cargo) {
        actualLoad = actualLoad - cargo.getWeight();
    }
}