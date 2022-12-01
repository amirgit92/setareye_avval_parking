package com.setare.avval.service;

import com.setare.avval.model.DTO.ReportDTO;
import com.setare.avval.model.PriceRate;
import com.setare.avval.repository.PriceRateRepository;
import com.setare.avval.model.Vehicle;
import com.setare.avval.model.VehicleDTO;
import com.setare.avval.repository.VehicleRepository;
import com.setare.avval.model.VehicleType;
import com.setare.avval.facilities.ParkingServiceFacilities;
import com.setare.avval.model.Parking;
import com.setare.avval.model.ParkingDTO;
import com.setare.avval.repository.ParkingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImp implements ParkingService {
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    PriceRateRepository priceRateRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public ParkingDTO enteringVehicle(VehicleDTO vehicleDTO) throws Exception {
        // validating vehicle type
        if (!(vehicleDTO.getVehicleType().equals(VehicleType.savari)) || !(vehicleDTO.getVehicleType().equals(VehicleType.tejari))) {
            throw new Exception("vehicle type is not valid!");
        }
        try {
            Vehicle newVehicle = mapper.map(vehicleDTO, Vehicle.class);
            vehicleRepository.save(newVehicle);
            //fetch the last priceRate
            PriceRate priceRate = priceRateRepository.findTopByOrderByIdDesc();
            Parking newParkingEntry = new Parking();
            newParkingEntry.setEntranceTime(ParkingServiceFacilities.setTime());
            newParkingEntry.setVehicle(newVehicle);
            newParkingEntry.setPriceRate(priceRate);
            parkingRepository.save(newParkingEntry);
            return mapper.map(newParkingEntry, ParkingDTO.class);
        } catch (Exception e) {
            throw new Exception("record saving failed. try again!");
        }
    }
    @Override
    public ParkingDTO exitingVehicle(VehicleDTO vehicleDTO) throws Exception {
        String exitTime = ParkingServiceFacilities.setTime();
        try {
            Parking parking = parkingRepository.findFirstByVehicleOrderByIdDesc(mapper.map(vehicleDTO, Vehicle.class));
            float price = ParkingServiceFacilities.priceCalculating(parking.getPriceRate().getPriceRateType(), parking.getEntranceTime(), exitTime);
            if (price == 0.0f) {
                throw new Exception("price calculation failed!");
            }
            parking.setParkingPrice(price);
            parking.setExitTime(exitTime);
            return mapper.map(parkingRepository.save(parking), ParkingDTO.class);
        } catch (Exception e) {
            throw new Exception("exiting process failed! please try again.");
        }
    }

    @Override
    public List<ReportDTO> report(ReportDTO reportDTO) throws Exception {
        ModelMapper mapper = new ModelMapper();
        try {
            List<Parking> parkings = parkingRepository
                    .findAllByVehicleAndEntranceTimeAndExitTime(reportDTO.getLicensePlate(), reportDTO.getEntrance(), reportDTO.getExit());
            return parkings.stream().map(parking -> mapper.map(parking, ReportDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("error in retrieving report! try again ");
        }
    }
}