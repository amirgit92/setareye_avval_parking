package com.setare.avval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String entranceTime;
    private String exitTime;
    private float parkingPrice;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "rate_id", nullable = false)
    private PriceRate priceRate;

    public Parking(String entranceTime, Vehicle vehicle, PriceRate priceRate) {
        this.entranceTime = entranceTime;
        this.vehicle = vehicle;
        this.priceRate = priceRate;
    }
}
