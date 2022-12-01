package com.setare.avval.facilities;

import com.setare.avval.model.PriceRateType;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ParkingFacilities {

    public static String setTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd - hh:mm a");
        return formatter.format(LocalDateTime.now());
    }

    public static float priceCalculating(PriceRateType priceRateType, String entranceTime, String exitTime) throws ParseException {

        float price = 0.0f;
        LocalTime entrance = LocalTime.parse(entranceTime);
        LocalDateTime exit = LocalDateTime.parse(exitTime);
        switch (priceRateType) {
            case hourly -> {
                // calculating the duration that the vehicle was in parking.
                long hours = ChronoUnit.HOURS.between(entrance, exit);
                long minutes = ChronoUnit.MINUTES.between(entrance, exit) % 60;
                //if the minutes is more than 30 minutes, normalize it to 1 hour!
                if (minutes > 30)
                    hours = ++hours;
                price = priceRateType.getRate() * hours;
            }
            case daily -> {
                long days = ChronoUnit.DAYS.between(entrance, exit);
                price = priceRateType.getRate() * days;
            }
            case monthly -> {
                long months = ChronoUnit.MONTHS.between(entrance, exit);
                price = priceRateType.getRate() * months;
            }
        }
        return price;
    }
}
