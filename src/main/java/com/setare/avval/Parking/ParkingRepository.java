package com.setare.avval.Parking;

import com.setare.avval.Vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    //fetching the last entrance row of the current vehicle
    Parking findFirstByVehicleOrderByIdDesc(Vehicle vehicle);

    //fetching all rows related to one vehicle in given time duration
    List<Parking> findAllByVehicleAndEntranceAndExit(Vehicle vehicle, LocalDateTime entrance, LocalDateTime exit);
}