package com.setare.avval.model.DTO;

import com.setare.avval.model.Vehicle;
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
