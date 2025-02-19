package es.minddata.challenge.entity;

import es.minddata.challenge.model.Cargo;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CargoVessel extends StarShip {

    private int maxCapacity;

    private int actualLoad;

    public void load(Cargo cargo) {
        actualLoad = actualLoad + cargo.getWeight();
    }

    public void unload(Cargo cargo) {
        actualLoad = actualLoad - cargo.getWeight();
    }


}