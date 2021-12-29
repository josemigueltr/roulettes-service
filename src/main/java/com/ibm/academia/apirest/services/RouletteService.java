package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.dtos.RouletteCreatedDTO;
import com.ibm.academia.apirest.models.dtos.RouletteDTO;
import com.ibm.academia.apirest.models.dtos.RouletteResultsDTO;

import java.util.List;

public interface RouletteService {

  public RouletteCreatedDTO createRoullete();
  public void openRoulette(Integer rouletteId);
  public List<RouletteDTO> listAllRoulettes();
  public RouletteResultsDTO closeRoulette(Integer rouletteId);
}
