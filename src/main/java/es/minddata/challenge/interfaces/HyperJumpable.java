package es.minddata.challenge.interfaces;

import es.minddata.challenge.entity.Engine;
import es.minddata.challenge.model.Position;
import es.minddata.challenge.model.StarSystem;

public interface HyperJumpable {
    Position calculateDestination(StarSystem starSystem);

    boolean checkEngine(Engine engine);

    boolean hyperJump(Position position);
}