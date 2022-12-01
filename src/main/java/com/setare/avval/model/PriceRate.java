package com.setare.avval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class PriceRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private PriceRateType priceRateType;
    private LocalDate localDate;
//    private float parkingPriceRate;

    @OneToMany(mappedBy = "priceRate")
    private List<Parking> parkings;

    public PriceRate(PriceRateType priceRateType,LocalDate localDate){
        this.priceRateType = priceRateType;
        this.localDate = localDate;
    }
}
