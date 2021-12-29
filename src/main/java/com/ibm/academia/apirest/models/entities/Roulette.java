package com.ibm.academia.apirest.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="Roulette")
public class Roulette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roulette_id")
    private Long  rouletteId;


    @Column(name = "state")
    private  boolean state;


    @Column(name = "total_money_bets")
    private  int totalMoneyBets;

    @OneToMany(mappedBy = "roulette", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties({"roulette"})
    private Set<Player> players = new HashSet<>();

    @Column(name = "date_update")
    @CreationTimestamp
    private Date dateUpdate;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @PrePersist
    private  void preCreated(){
        this.dateCreated=new Date();
    }

    @PreUpdate
    private  void preUpdate(){
        this.dateUpdate=new Date();
    }



}
