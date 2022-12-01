package com.setare.avval.facilities;

import com.setare.avval.model.Parking;
import com.setare.avval.repository.PriceRateRepository;
import com.setare.avval.model.Vehicle;
import com.setare.avval.model.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class DataBaseSeed extends Thread {

    @Autowired
    PriceRateRepository priceRateRepository;
    private Thread newThread;
    private String threadName;

    public DataBaseSeed(String threadName) {
        this.threadName = threadName;
    }

    public void run() {
        try {
            while (true) {
                for (int i=0;i<3;i++){
                    Vehicle newVehicle = new Vehicle(randomVehicleTypeGenerator(),licensePlate());
                    Parking newEntrance =
                            new Parking(ParkingServiceFacilities.setTime(),newVehicle,priceRateRepository.findTopByOrderByIdDesc());
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            System.out.println("the memory usage thread is exited! error: " + e.getMessage());
        }
    }

    public void start() {
        if (this.newThread == null) {
            newThread = new Thread(this, this.threadName);
            newThread.start();
        }
    }

    /* generating 6 lingth character alphanumeric license plate
       in 12X123 pattern
     */
    public String licensePlate(){
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder s = new StringBuilder(6);
        Random r = new Random();
        int i = r.nextInt(89) + 10;
        s.append(i);
        int ch = (int) (AlphaNumericStr.length() * Math.random());
        s.append(AlphaNumericStr.charAt(ch));
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
