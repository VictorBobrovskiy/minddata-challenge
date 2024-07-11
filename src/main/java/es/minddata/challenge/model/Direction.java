package es.minddata.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Direction {

    private Position positionTo;

    public Direction(Position positionTo) {
        this.positionTo = positionTo;
    }
}
