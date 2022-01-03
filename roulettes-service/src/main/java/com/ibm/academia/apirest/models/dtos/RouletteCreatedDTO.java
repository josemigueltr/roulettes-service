package com.ibm.academia.apirest.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RouletteCreatedDTO {

        private final String SATISFACTORY_MESSAGE="Ruleta Creada Con Exito";
        @JsonProperty("ID_ruleta")
        private Long rouletteDtoId;

}
