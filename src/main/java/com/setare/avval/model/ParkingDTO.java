package com.setare.avval.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingDTO {
    private Vehicle licensePlate;
    private Vehicle vehicleType;
    private String entranceTime;
    private String exitTime;
    private float parkingPrice;
}
