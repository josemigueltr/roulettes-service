package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.exeptiontypes.BadRequestException;
import com.ibm.academia.apirest.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.apirest.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.apirest.models.entities.Player;
import com.ibm.academia.apirest.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public  PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    /**
     * Endpoint poder añadir la apuesta de un jugador a una ruleta abierta
     * @param rouletteId  identificador de la ruleta a la que se va a postar
     * @param player  onjeto de tipo player que contendra toda la informacion del jugador
     * @throws NotFoundException si la ruleta a apostar no existe no existe
     * @throws BadRequestException si el dinero a apostar es mayor que 10000, el color no es rojo y negro
     * el numero a apostar no esta entre el rango que maneja la ruleta
     * @return Retorna un DTO con la informacion necesaria para que el cliente pueda interactuar con esa ruleta
     */
    @PostMapping("/register/{rouletteId}")
    public ResponseEntity<?> roulettePlayer1(@PathVariable Integer rouletteId,
                                             @RequestBody Player player) {
        try {
            playerService.addPlayerRoullete(rouletteId,player);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ExceptionType exception){
            throw  exception;
        }
    }
}
