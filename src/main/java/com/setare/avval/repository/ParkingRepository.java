package com.setare.avval.repository;

import com.setare.avval.model.Vehicle;
import com.setare.avval.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    //fetching the last entrance row of the current vehicle
    Parking findFirstByVehicleOrderByIdDesc(Vehicle vehicle);

    //fetching all rows related to one vehicle in given time duration
    List<Parking> findAllByVehicleAndEntranceTimeAndExitTime(Vehicle vehicle, String entrance, String exit);
}