package com.setare.avval.Parking.DTO;

import com.setare.avval.Vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class ReportDTO {
    LocalDateTime entrance;
    LocalDateTime exit;
    Vehicle licensePlate;
    float parkingPriceRate;
}
