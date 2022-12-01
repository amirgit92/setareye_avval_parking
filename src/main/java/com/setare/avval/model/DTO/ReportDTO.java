package com.setare.avval.model.DTO;

import com.setare.avval.model.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportDTO {
    String entrance;
    String exit;
    Vehicle licensePlate;
    float parkingPriceRate;
}
