package com.ibm.academia.apirest.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RouletteDTO {

    @JsonProperty("Id_ruleta")
    private Long rouletteDtoId;

    @JsonProperty("Estado_ruleta")
    private String rouletteState;

    @JsonProperty("Numero_jugadores")
    private int roulettePlayers;

    @JsonProperty("Monto_total_ruleta")
    private int rouletteTotalMount;
}
