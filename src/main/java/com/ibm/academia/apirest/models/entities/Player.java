package com.ibm.academia.apirest.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apirest.enums.Color;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashSet;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;


    @Column(name = "number_bet")
    @Min(value=1, message = "El minimo numero para apostar es 1")
    @Max(value=36, message = "El maximo numero para apostar es 36")
    private  int numberBet;


    @Column(name = "money_bet")
    @Min(value=0, message = "Se debe de apostar algo de dinero")
    @Max(value=10000, message = "El monto maximo para apostar es de 10000 pesos")
    private  int moneyBet;


    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;


    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "roulette_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "players"})
    private Roulette roulette;

    public  void setColor(int color){
        this.color=(color==0)? Color.ROJO:Color.NEGRO;
    }

}
