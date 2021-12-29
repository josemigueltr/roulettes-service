package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.enums.Color;
import com.ibm.academia.apirest.exceptions.exeptiontypes.BadRequestException;
import com.ibm.academia.apirest.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.apirest.mapper.RouletteMapper;
import com.ibm.academia.apirest.models.dtos.RouletteCreatedDTO;
import com.ibm.academia.apirest.models.dtos.RouletteDTO;
import com.ibm.academia.apirest.models.dtos.RouletteResultsDTO;
import com.ibm.academia.apirest.models.entities.Player;
import com.ibm.academia.apirest.models.entities.Roulette;
import com.ibm.academia.apirest.repositories.RouletteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RouletteServiceImp implements RouletteService {

    private RouletteRepository rouletteRepository;
    @Autowired
    public  RouletteServiceImp(RouletteRepository rouletteRepository){
        this.rouletteRepository=rouletteRepository;
    }

    @Override
    public RouletteCreatedDTO createRoullete() {
        Roulette roulette= rouletteRepository.save(new Roulette());
        RouletteCreatedDTO rouletteDTO = RouletteCreatedDTO.builder().rouletteDtoId(roulette.getRouletteId()).build();
        return rouletteDTO;
    }

    @Override
    public void openRoulette(Integer rouletteId) {
        Optional<Roulette> rouletteOptional = rouletteRepository.findRouletteByRouletteId(rouletteId.longValue());
        if(rouletteOptional.isPresent()){
            Roulette roulette=rouletteOptional.get();
            if(roulette.isState())
                throw new BadRequestException(HttpStatus.BAD_REQUEST,
                        String.format("La ruleta con el id: %d ya esta abierta",rouletteId));
            roulette.setState(true);
            rouletteRepository.save(roulette);
        }else{
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    String.format("La ruleta con el id: %d no existe",rouletteId));
        }
    }

    @Override
    public List<RouletteDTO> listAllRoulettes() {
        List<Roulette> roulettes=(List<Roulette>)rouletteRepository.getAllRoulletes();
        List<RouletteDTO> rouletteDTOS=roulettes.stream().map(r -> RouletteMapper.mapRouletteDTO(r))
                                                                    .collect(Collectors.toList());
        return rouletteDTOS;
    }

    @Override
    public RouletteResultsDTO closeRoulette(Integer rouletteId) {
        Optional<Roulette> rouletteOptional = rouletteRepository.findRouletteByRouletteId(rouletteId.longValue());
        if(rouletteOptional.isPresent()){
            Roulette roulette=rouletteOptional.get();
            if(!roulette.isState())
                throw new BadRequestException(HttpStatus.BAD_REQUEST,
                        String.format("La ruleta con el id: %d ya se encuentra cerrada",rouletteId));
            roulette.setState(false);
            RouletteResultsDTO rouletteResultsDTO=calculaResultados(roulette);
            return rouletteResultsDTO;
        }else {
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    String.format("La ruleta con el id: %d no existe", rouletteId));
        }
    }


    private RouletteResultsDTO calculaResultados(Roulette roulette){
        Random random = new Random();
        int max=36;
        int min=1;
        int numGanador = random.nextInt(max - min) + min;
        int numColorGanador=random.nextInt(2);
        Color colorGanador=(numColorGanador==0)? Color.ROJO:Color.NEGRO;
        List<Player>winPlayers=roulette.getPlayers().stream()
                .filter(p -> (p.getNumberBet()==numGanador && p.getColor()==colorGanador)).collect(Collectors.toList());
        RouletteResultsDTO rouletteResultsDTO= RouletteMapper.mapRouletteResultsDTO(roulette,winPlayers.size(),numGanador,colorGanador);

        return rouletteResultsDTO;
    }

}




