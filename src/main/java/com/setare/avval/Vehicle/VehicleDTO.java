package com.setare.avval.Vehicle;

import com.setare.avval.PriceRate.PriceRateType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleDTO {
    private String licensePlate;
    VehicleType vehicleType;
    PriceRateType priceRateType;
}
