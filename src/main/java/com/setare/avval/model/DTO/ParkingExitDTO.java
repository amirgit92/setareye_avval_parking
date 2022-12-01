package com.setare.avval.model.DTO;

import com.setare.avval.model.PriceRate;
import com.setare.avval.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingExitDTO {
    private String licensePlate;
    Vehicle vehicle;
    PriceRate priceRate;
}
