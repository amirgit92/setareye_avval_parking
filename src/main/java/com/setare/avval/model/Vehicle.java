package com.setare.avval.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private VehicleType vehicleType;
    private String licensePlate;

    @OneToMany(mappedBy = "vehicle")
    private List<Parking> parking;

    public Vehicle(VehicleType vehicleType, String licensePlate) {
        this.setVehicleType(vehicleType);
        this.licensePlate = licensePlate;
    }
}
