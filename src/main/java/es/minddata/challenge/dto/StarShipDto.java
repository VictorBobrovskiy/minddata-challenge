package es.minddata.challenge.dto;

import es.minddata.challenge.entity.Engine;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Datos de naves para crear o modificar")
public class StarShipDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Id de la nave")
    private Long id;

    @NotNull(message = "La nave debe tener un parametro de nombre")
    @NotBlank(message = "El nombre no debe ser menor de 2 letras")
    @Size(min = 2)
    @ApiModelProperty(notes = "Nombre de la nave")
    private String name;

    @NotNull(message = "La nave debe tener un parametro de velocidad maxima")
    @PositiveOrZero(message = "Velocidad maxima debe ser mayor a cero")
    @ApiModelProperty(notes = "Velocidad maxima de la nave")
    private int maxSpeed;

    @NotNull
    @Positive(message = "Velocidad maxima debe ser mayor a cero")
    @ApiModelProperty(notes = "Peso de la nave")
    private int weight;

    @NotNull
    @Positive(message = "Velocidad maxima debe ser mayor a cero")
    @ApiModelProperty(notes = "Tamano de la nave")
    private int size;

    @NotNull
    @PositiveOrZero(message = "Velocidad maxima debe ser mayor a cero")
    @ApiModelProperty(notes = "Fuerza del escudo e")
    private int shieldStrength;

    @NotNull
    @ApiModelProperty(notes = "Nombre del motor")
    private Engine engine;

    @Positive(message = "Capacidad maxima debe ser mayor a cero")
    @ApiModelProperty(notes = "Capacidad máxima")
    private Integer maxCapacity;

    @PositiveOrZero(message = "Carga efectiva debe ser cero o mayor")
    @ApiModelProperty(notes = "Carga efectiva")
    private Integer actualLoad;

    @Positive(message = "Potencia maxima debe ser mayor a cero")
    @ApiModelProperty(notes = "Potencia máxima de la nave")
    private Integer maxGunPower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarShipDto that = (StarShipDto) o;
        return maxSpeed == that.maxSpeed &&
                weight == that.weight &&
                size == that.size &&
                shieldStrength == that.shieldStrength &&
                Objects.equals(name, that.name) &&
                Objects.equals(engine, that.engine) &&
                Objects.equals(maxCapacity, that.maxCapacity) &&
                Objects.equals(actualLoad, that.actualLoad) &&
                Objects.equals(maxGunPower, that.maxGunPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxSpeed, weight, size, shieldStrength, engine, maxCapacity, actualLoad, maxGunPower);
    }

    @Override
    public String toString() {
        return "StarShipDto{" +
                "name='" + name + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", weight=" + weight +
                ", size=" + size +
                ", shieldStrength=" + shieldStrength +
                ", engine=" + engine +
                ", maxCapacity=" + maxCapacity +
                ", actualLoad=" + actualLoad +
                ", maxGunPower=" + maxGunPower +
                '}';
    }
}
