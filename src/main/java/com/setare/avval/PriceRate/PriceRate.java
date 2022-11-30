package com.setare.avval.PriceRate;

import com.setare.avval.Parking.Parking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class PriceRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    PriceRateType priceRateType;
    LocalDate localDate;
    private float parkingPriceRate;

    @OneToMany(mappedBy = "priceRate")
    private List<Parking> parkings;
}
