package com.setare.avval.Parking.DTO;

import com.setare.avval.PriceRate.PriceRate;
import com.setare.avval.Vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingExitDTO {
    private String licensePlate;
    Vehicle vehicle;
    PriceRate priceRate;
}
