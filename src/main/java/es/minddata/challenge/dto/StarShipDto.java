package es.minddata.challenge.dto;

import es.minddata.challenge.entity.Engine;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StarShipDto {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2)
    private String name;

    @NotNull
    @PositiveOrZero
    private int maxSpeed;

    @NotNull
    @Positive
    private int weight;

    @NotNull
    @Positive
    private int size;

    @NotNull
    @PositiveOrZero
    private int shieldStrength;

    @NotNull
    private Engine engine;

    @PositiveOrZero
    private Integer maxCapacity;

    @Positive
    private Integer actualLoad;

    @Positive
    private Integer maxGunPower;

}
