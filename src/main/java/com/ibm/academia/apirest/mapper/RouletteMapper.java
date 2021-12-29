package com.ibm.academia.apirest.mapper;

import com.ibm.academia.apirest.enums.Color;
import com.ibm.academia.apirest.models.dtos.RouletteDTO;
import com.ibm.academia.apirest.models.dtos.RouletteResultsDTO;
import com.ibm.academia.apirest.models.entities.Roulette;

public class RouletteMapper {
    /**
     * Metodo para mappear obtetos de tipo roulette a rouletteDTO
     * @param roulette  ruleta que se va mappear
     * @return Retorna un DTO de una ruleta que  contendra la informacion que se le  mandará al cliente
     */
    public static RouletteDTO mapRouletteDTO (Roulette roulette){
        RouletteDTO rouletteDTO= RouletteDTO.builder()
                                           .rouletteDtoId(roulette.getRouletteId())
                                           .roulettePlayers(roulette.getPlayers().size())
                                           .rouletteState((roulette.isState())? "Abierta":"Cerrada")
                                           .rouletteTotalMount(roulette.getTotalMoneyBets())
                                           .build();
        return  rouletteDTO;
    }

    /**
     * Metodo para mappear obtetos de tipo roulette a rouletteResultsDTO
     * @param roulette  ruleta que se va mappear
     * @return Retorna un DTO de una ruleta que se ha cerrado, contiene toda la información corespondiente a los resultados.
     */
    public static RouletteResultsDTO  mapRouletteResultsDTO(Roulette roulette, int totalWinners, int numWinner, Color colorwinner){

        RouletteResultsDTO rouletteDTO= RouletteResultsDTO.builder()
                .rouletteDtoId(roulette.getRouletteId())
                .roulettePlayers(roulette.getPlayers().size())
                .rouletteState((roulette.isState())? "Abierta":"Cerrada")
                .rouletteTotalMount(roulette.getTotalMoneyBets())
                .rouletteNumWinner(numWinner)
                .rouletteTotalWinners(totalWinners)
                .rouletteColorWinner(colorwinner)
                .build();
        return  rouletteDTO;

    }
}
