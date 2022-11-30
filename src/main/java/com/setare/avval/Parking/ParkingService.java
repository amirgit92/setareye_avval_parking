package com.setare.avval.Parking;

import com.setare.avval.Parking.DTO.ParkingEntranceDTO;
import com.setare.avval.Parking.DTO.ParkingExitDTO;
import com.setare.avval.Parking.DTO.ReportDTO;
import com.setare.avval.Vehicle.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingService {
    ParkingDTO enteringVehicle(VehicleDTO vehicleDTO) throws Exception;

    ParkingDTO exitingVehicle(VehicleDTO vehicleDTO) throws Exception;

    List<ReportDTO> report(ReportDTO reportDTO) throws Exception;
}
