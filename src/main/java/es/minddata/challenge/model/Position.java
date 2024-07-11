package es.minddata.challenge.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Position {
    private double x;
    private double y;
    private double z;
}