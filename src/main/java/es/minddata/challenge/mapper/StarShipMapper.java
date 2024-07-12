package es.minddata.challenge.mapper;

import es.minddata.challenge.dto.StarShipDto;
import es.minddata.challenge.entity.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StarShipMapper {

    private final StarShipFabric starShipFabric = new StarShipFabric();


    public static StarShipDto convertToDto(StarShip starShip) {

        StarShipDto dto = new StarShipDto();

        dto.setId(starShip.getId());
        dto.setName(starShip.getName());
        dto.setMaxSpeed(starShip.getMaxSpeed());
        dto.setWeight(starShip.getWeight());
        dto.setSize(starShip.getSize());
        dto.setShieldStrength(starShip.getShieldStrength());
        dto.setEngine(starShip.getEngine());

        if (starShip instanceof CargoVessel) {
            CargoVessel cv = (CargoVessel) starShip;
            dto.setMaxCapacity(cv.getMaxCapacity());
            dto.setActualLoad(cv.getActualLoad());
        }
        if (starShip instanceof WarShip) {
            WarShip ws = (WarShip) starShip;
            dto.setMaxGunPower(ws.getMaxGunPower());
        }
        if (starShip instanceof ArtifactShip) {
            ArtifactShip as = (ArtifactShip) starShip;
            dto.setMaxCapacity(as.getMaxCapacity());
            dto.setActualLoad(as.getActualLoad());
            dto.setMaxGunPower(as.getMaxGunPower());
        }
        return dto;
    }

    public static StarShip createEntityFromDto(StarShipDto dto) {

        if (dto.getMaxCapacity() != null && dto.getMaxGunPower() != null) {

            ArtifactShip ship = starShipFabric.createArtifactShip(
                    dto.getName(),
                    dto.getMaxSpeed(),
                    dto.getWeight(),
                    dto.getSize(),
                    dto.getShieldStrength(),
                    dto.getEngine(),
                    dto.getMaxCapacity(),
                    dto.getActualLoad(),
                    dto.getMaxGunPower()
            );
            return ship;
        } else if (dto.getMaxCapacity() != null) {
            CargoVessel ship = starShipFabric.createCargoVessel(
                    dto.getName(),
                    dto.getMaxSpeed(),
                    dto.getWeight(),
                    dto.getSize(),
                    dto.getShieldStrength(),
                    dto.getEngine(),
                    dto.getMaxCapacity(),
                    dto.getActualLoad()
            );
            return ship;
        } else {
            WarShip ship = starShipFabric.createWarShip(
                    dto.getName(),
                    dto.getMaxSpeed(),
                    dto.getWeight(),
                    dto.getSize(),
                    dto.getShieldStrength(),
                    dto.getEngine(),
                    dto.getMaxGunPower()
            );
            return ship;
        }
    }

}
