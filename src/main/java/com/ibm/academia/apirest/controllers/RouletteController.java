package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.exceptions.exeptiontypes.BadRequestException;
import com.ibm.academia.apirest.exceptions.exeptiontypes.ExceptionType;
import com.ibm.academia.apirest.exceptions.exeptiontypes.NotFoundException;
import com.ibm.academia.apirest.models.dtos.RouletteCreatedDTO;
import com.ibm.academia.apirest.models.dtos.RouletteDTO;
import com.ibm.academia.apirest.models.dtos.RouletteResultsDTO;
import com.ibm.academia.apirest.services.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roulette")
public class RouletteController {
    private RouletteService rouletteService;

    @Autowired
    public  RouletteController(RouletteService rouletteService){
        this.rouletteService=rouletteService;
    }


    /**
     * Endpoint para crear una nueva ruleta
     * @return Retorna un DTO con la informacion necesaria para que el cliente pueda interactuar con esa ruleta
     */
    @GetMapping("/create")
    public ResponseEntity<?> createRoulette() {
        try {
            RouletteCreatedDTO newRoulette= rouletteService.createRoullete();
            return new ResponseEntity<>(newRoulette, HttpStatus.OK);
        }catch(ExceptionType exception){
            throw  exception;
        }
    }

    /**
     * Endpoint poder abrir una ruleta cuyo estado sea False
     * @Param id identificador de la ruleta que se quiere abrir
     * @throws NotFoundException si la ruleta a abrir no existe
     * @throws BadRequestException si la ruleta a abrir ya se encontraba abierta
     * @return Retorna un DTO con la informacion necesaria para que el cliente pueda interactuar con esa ruleta
     */
    @GetMapping("/open/{id}")
    public ResponseEntity<?> openRoulette(@PathVariable Integer id) {
        try {
            rouletteService.openRoulette(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ExceptionType exception){
            throw  exception;
        }
    }

    /**
     * Endpoint poder obtener informacion sobre todas las ruletas que se han creado
     * @return Retorna na lista con la informacion de todas la tarjetas que se han creado
     */
    @GetMapping("/getall")
    public ResponseEntity<?> getAllRoulettes() {
        try {
            List<RouletteDTO> rouletteDTOS=rouletteService.listAllRoulettes();
            return new ResponseEntity<>(rouletteDTOS,HttpStatus.OK);
        }catch(ExceptionType exception){
            throw  exception;
        }
    }


    /**
     * Endpoint poder cerrar una ruleta que este abierta
     * @param id  identificador de la ruleta que se quiere cerrar
     * @throws NotFoundException si la ruleta a cerrar no existe
     * @return  Retorna un objeto dto que contiene toda los resultados de las apuestas de la ruleta
     */
    @GetMapping("/close/{id}")
    public ResponseEntity<?> closedRoulette(@PathVariable Integer id) {
        try {
             RouletteResultsDTO rouletteDTOS=rouletteService.closeRoulette(id);
            return new ResponseEntity<>(rouletteDTOS,HttpStatus.OK);
        }catch(ExceptionType exception){
            throw  exception;
        }
    }




}
