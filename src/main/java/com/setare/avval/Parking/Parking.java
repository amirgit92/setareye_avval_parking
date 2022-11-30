package com.setare.avval.Parking;

import com.setare.avval.PriceRate.PriceRate;
import com.setare.avval.Vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
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
    @JoinColumn(name = "rate_id",nullable = false)
    private PriceRate priceRate;
}
