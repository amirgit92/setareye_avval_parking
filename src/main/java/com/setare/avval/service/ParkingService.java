package com.setare.avval.service;
import com.setare.avval.model.DTO.ReportDTO;
import com.setare.avval.model.VehicleDTO;
import com.setare.avval.model.DTO.ParkingDTO;

import java.util.List;

public interface ParkingService {
    ParkingDTO enteringVehicle(VehicleDTO vehicleDTO) throws Exception;

    ParkingDTO exitingVehicle(VehicleDTO vehicleDTO) throws Exception;

    List<ReportDTO> report(ReportDTO reportDTO) throws Exception;
}
