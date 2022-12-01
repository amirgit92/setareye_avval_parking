package com.setare.avval.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleDTO {
    private String licensePlate;
    VehicleType vehicleType;
    PriceRateType priceRateType;
}
