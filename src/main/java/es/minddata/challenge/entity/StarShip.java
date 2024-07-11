package es.minddata.challenge.entity;

import es.minddata.challenge.interfaces.HyperJumpable;
import es.minddata.challenge.interfaces.Landable;
import es.minddata.challenge.model.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class StarShip implements Landable, HyperJumpable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Embedded
    private transient Position position;
    @ManyToOne
    private Engine engine;

    private int maxSpeed;
    private int weight;
    private int size;
    private int shieldStrength;

    public void move(int speed, Direction direction) {
        System.out.println("Moving to " + direction + " with speed: " + speed);
    }

    @Override
    public Position calculateDestination(StarSystem starSystem) {
        return null;
    }

    @Override
    public boolean checkEngine(Engine engine) {
        return engine.isHyper();
    }

    @Override
    public boolean hyperJump(Position position) {
        if (position.equals(this.getPosition()))
            return false;
        else {
            this.setPosition(position);
            return true;
        }
    }

    @Override
    public boolean land(Planet planet, Location location) {
        return false;
    }

    @Override
    public boolean land(CosmoPort cosmoPort) {
        if (cosmoPort != null && !cosmoPort.isOpen()) {
            try {
                cosmoPort.setOpen(true);
                return true;
            } catch (Exception e) {
                System.out.println("CosmoPort refused landing");
                return false;
            }
        } else
            return true;
    }

}