package es.minddata.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarSystem {

    private String star;

    private String galaxy;

    private Planet[] planets;
}
