package es.minddata.challenge.entity;

import es.minddata.challenge.model.Direction;
import es.minddata.challenge.entity.Engine;
import es.minddata.challenge.model.Position;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class StarShip {
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

}