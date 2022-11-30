package com.setare.avval.Vehicle;

import com.setare.avval.Parking.Parking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private VehicleType vehicleType;
    private String licensePlate;

    @OneToMany(mappedBy = "vehicle")
    private List<Parking> parking;
}
