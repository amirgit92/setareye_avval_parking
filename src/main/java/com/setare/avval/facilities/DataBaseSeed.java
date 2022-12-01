package com.setare.avval.facilities;

import com.setare.avval.model.*;
import com.setare.avval.repository.ParkingRepository;
import com.setare.avval.repository.PriceRateRepository;
import com.setare.avval.repository.VehicleRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Random;

public class DataBaseSeed extends Thread {

    @Autowired
    PriceRateRepository priceRateRepository;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    private Thread newThread;
    private String threadName;

    public DataBaseSeed(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        try {
            //seeding price rate table
            PriceRate newHourlyPriceRate = new PriceRate(PriceRateType.hourly, LocalDate.now());
            PriceRate newDailyPriceRate = new PriceRate(PriceRateType.daily, LocalDate.now());
            PriceRate newMonthlyPriceRate = new PriceRate(PriceRateType.monthly, LocalDate.now());
            priceRateRepository.save(newHourlyPriceRate);
            priceRateRepository.save(newDailyPriceRate);
            priceRateRepository.save(newMonthlyPriceRate);

            Vehicle newVehicle = new Vehicle();
            Parking newEntrance = new Parking();
            while (true) {
                for (int i=0;i<3;i++){
                    newVehicle.setVehicleType(randomVehicleTypeGenerator());
                    newVehicle.setLicensePlate(licensePlate());
                    vehicleRepository.save(newVehicle);

                    newEntrance.setEntranceTime(ParkingFacilities.setTime());
                    newEntrance.setVehicle(newVehicle);
                    newEntrance.setPriceRate(priceRateRepository.findTopByOrderByIdDesc());
                    parkingRepository.save(newEntrance);
                }
                //each 5seconds, 3 vehicles enter to the parking
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println("seeding DB error! " + e.getMessage());
        }
    }

    public void start() {
        if (this.newThread == null) {
            newThread = new Thread(this, this.threadName);
            newThread.start();
        }
    }

    /* generating 6char length alphanumeric license plate
       in 12X123 pattern
     */
    public String licensePlate(){
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder s = new StringBuilder(6);
        Random r = new Random();
        //generating 2digit numbers between 10 and 99
        int i = r.nextInt(89) + 10;
        s.append(i);
        int ch = (int) (AlphaNumericStr.length() * Math.random());
        s.append(AlphaNumericStr.charAt(ch));
        //generating 3digit numbers between 100 and 999
        int j = r.nextInt(898) + 100;
        s.append(j);
        return s.toString();
    }

    // using in order to seed the DB
    public VehicleType randomVehicleTypeGenerator(){
        Random r = new Random();
            int i = r.nextInt(2)+1 ;
            if (i == 1)
                return VehicleType.savari;
            else
                return  VehicleType.tejari;
    }
}
