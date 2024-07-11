package es.minddata.challenge.entity;

import jakarta.persistence.Entity;

@Entity
public class WarShip extends StarShip {

    private int maxGunPower;

    public boolean fire(StarShip starShip, int gunPower) {

        System.out.println("Piu, piu, piu");

        int reamingShield = starShip.getShieldStrength() - gunPower;

        if (starShip.getShieldStrength() < 0)
            return true;
            else return false;
    }

}