package es.minddata.challenge.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WarShip extends StarShip {

    private int maxGunPower;

    public boolean fire(StarShip starShip, int gunPower) {

        System.out.println("Piu, piu, piu");

        int reamingShield = starShip.getShieldStrength() - gunPower;

        if (reamingShield < 0)
            return true;
        else {
            starShip.setShieldStrength(reamingShield);
            return false;
        }
    }
}