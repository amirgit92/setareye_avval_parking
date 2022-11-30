package com.setare.avval.Parking;

import com.setare.avval.Vehicle.Vehicle;
import com.setare.avval.Vehicle.VehicleType;
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
