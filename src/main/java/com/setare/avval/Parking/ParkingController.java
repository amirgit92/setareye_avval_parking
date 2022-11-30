package com.setare.avval.Parking;

import com.setare.avval.Parking.DTO.ParkingEntranceDTO;
import com.setare.avval.Parking.DTO.ParkingExitDTO;
import com.setare.avval.Parking.DTO.ReportDTO;
import com.setare.avval.Vehicle.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    @Autowired
    ParkingService parkingService;

    public ResponseEntity<?> enteringVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(parkingService.enteringVehicle(vehicleDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    public ResponseEntity<?> exitingVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(parkingService.exitingVehicle(vehicleDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    public ResponseEntity<?> report(@RequestBody ReportDTO reportDTO) {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(parkingService.report(reportDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
