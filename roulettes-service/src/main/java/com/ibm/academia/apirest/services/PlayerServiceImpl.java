package com.ibm.academia.apirest.services;


import com.ibm.academia.apirest.exceptions.exeptiontypes.BadRequestException;
import com.ibm.academia.apirest.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.apirest.models.entities.Player;
import com.ibm.academia.apirest.models.entities.Roulette;
import com.ibm.academia.apirest.repositories.RouletteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    private RouletteRepository rouletteRepository;

    @Autowired
    public  PlayerServiceImpl(RouletteRepository rouletteRepository){
        this.rouletteRepository=rouletteRepository;
    }

    @Override
    public void addPlayerRoullete(Integer rouletteId, Player player) {
        Optional<Roulette> rouletteOptional = rouletteRepository.findRouletteByRouletteId(rouletteId.longValue());
        if(rouletteOptional.isPresent()){
            Roulette roulette = rouletteOptional.get();
            if(!roulette.isState())
                throw new BadRequestException(HttpStatus.BAD_REQUEST,
                        String.format("La ruleta con el id: %d se encuentra cerrada",rouletteId));
            try {
                player.setRoulette(roulette);
                roulette.getPlayers().add(player);
                int newMount=roulette.getTotalMoneyBets()+player.getMoneyBet();
                roulette.setTotalMoneyBets(newMount);
                rouletteRepository.save(roulette);
            }catch (Exception e){
                throw  new BadRequestException(HttpStatus.BAD_REQUEST,e.getMessage());
            }
        }else{
            throw new NotFoundException(HttpStatus.NOT_FOUND,
                    String.format("La ruleta con el id: %d no existe",rouletteId));
        }
    }

}
