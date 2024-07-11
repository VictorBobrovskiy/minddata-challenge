package es.minddata.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CosmoPort {

    private String name;

    private Planet planet;

    private boolean isOpen;

    private Location location;
}
