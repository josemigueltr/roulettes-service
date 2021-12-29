package com.ibm.academia.apirest.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.academia.apirest.enums.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RouletteResultsDTO {

    @JsonProperty("Id_ruleta")
    private Long rouletteDtoId;

    @JsonProperty("Estado_ruleta")
    private String rouletteState;

    @JsonProperty("Monto_total_ruleta")
    private int rouletteTotalMount;

    @JsonProperty("Numero_jugadores")
    private int roulettePlayers;

    @JsonProperty("Numero_ganadores")
    private int rouletteTotalWinners;

    @JsonProperty("Color_elegido")
    private Color rouletteColorWinner;

    @JsonProperty("Numero_elegido")
    private int rouletteNumWinner;
}