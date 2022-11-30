package com.setare.avval.Parking;

import com.setare.avval.Parking.DTO.ReportDTO;
import com.setare.avval.PriceRate.PriceRate;
import com.setare.avval.PriceRate.PriceRateRepository;
import com.setare.avval.PriceRate.PriceRateType;
import com.setare.avval.Vehicle.Vehicle;
import com.setare.avval.Vehicle.VehicleDTO;
import com.setare.avval.Vehicle.VehicleRepository;
import com.setare.avval.Vehicle.VehicleType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            newParkingEntry.setEntranceTime(setTime());
            newParkingEntry.setVehicle(newVehicle);
            newParkingEntry.setPriceRate(priceRate);
            parkingRepository.save(newParkingEntry);
            return mapper.map(newParkingEntry, ParkingDTO.class);
        } catch (Exception e) {
            throw new Exception("record saving failed. try again!");
        }
    }

    /*todo: should calculate the price in function depends on the hour and the rate
        also should check if the payment was successful, if not return the roper message
        and does not save(update) the record in DB.
        for updating, first the record should be fetched and then manipulated.
     */
    @Override
    public ParkingDTO exitingVehicle(VehicleDTO vehicleDTO) throws Exception {
        String exitTime = setTime();
        try {
            Parking parking = parkingRepository.findFirstByVehicleOrderByIdDesc(mapper.map(vehicleDTO, Vehicle.class));
            float price = priceCalculating(parking.getPriceRate().getPriceRateType(), parking.getEntranceTime(), exitTime);
            parking.setParkingPrice(price);
            parking.setExitTime(exitTime);
            return mapper.map(parkingRepository.save(parking), ParkingDTO.class);
        } catch (Exception e) {
            throw new Exception("exiting calculation failed! please try again.");
        }
    }

    @Override
    public List<ReportDTO> report(ReportDTO reportDTO) throws Exception {
        ModelMapper mapper = new ModelMapper();
        try {
            List<Parking> parkings = parkingRepository.findAllByVehicleAndEntranceAndExit(reportDTO.getLicensePlate(), reportDTO.getEntrance(), reportDTO.getExit());
            return parkings.stream().map(parking -> mapper.map(parking, ReportDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("error in retrieving report! try again ");
        }

    }

    private String setTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        return formatter.format(LocalDateTime.now());
    }


    private float priceCalculating(PriceRateType priceRateType, String entranceTime, String exitTime) throws ParseException {

        float price;
        switch (priceRateType) {
            case hourly -> {
                // calculating the duration that the vehicle was in parking.
                LocalTime entrance = LocalTime.parse(entranceTime);
                LocalTime exit = LocalTime.now();
                long hours = ChronoUnit.HOURS.between(entrance, exit);
                long minutes = ChronoUnit.MINUTES.between(entrance, exit) % 60;
                //if the minutes is more than 30 minutes, normalize it to 1 hour!
                if (minutes > 30)
                    hours += ++hours;
                price = priceRateType.getRate() * hours;
            }
            case daily -> {

            }
        }

        return 0.0f;
    }
}