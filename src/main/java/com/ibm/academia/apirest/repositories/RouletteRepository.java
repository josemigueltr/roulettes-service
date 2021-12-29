package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.models.entities.Roulette;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouletteRepository extends CrudRepository<Roulette,Long> {

        public Optional<Roulette> findRouletteByRouletteId(Long roulette_id);

        @Query("SELECT r FROM Roulette r")
        public Iterable<Roulette> getAllRoulletes();
}
