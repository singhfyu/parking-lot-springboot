package com.singhfyu.parkinglot.domain;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DayNightPricingStrategy implements PricingStrategy{
    public long calculateFee(Ticket ticket, LocalDateTime exitTime){
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        LocalDateTime entryTime= ticket.getEntryTime();

        Duration totalDuration = Duration.between(entryTime,exitTime);
        Duration dayDuration = dayDuration(entryTime, exitTime);
        Duration nightDuration = totalDuration.minus(dayDuration);

        long dayChargePerHour;
        long nightChargePerHour;
        long dayCharge=0;
        long nightCharge=0;

        if(parkingSpot.getSpotType()==SpotType.COMPACT){
            dayChargePerHour =60;
            nightChargePerHour =40;
        }
        else if(parkingSpot.getSpotType()==SpotType.REGULAR){
            dayChargePerHour =120;
            nightChargePerHour =80;
        }
        else if(parkingSpot.getSpotType()==SpotType.OVERSIZE){
            dayChargePerHour =180;
            nightChargePerHour =120;
        }else{
            throw new IllegalStateException("Unknown spot type");
        }
        dayCharge=dayChargePerHour * (long)(Math.ceil(dayDuration.toMinutes()/60.0)) ;
        nightCharge=nightChargePerHour * (long)(Math.ceil(nightDuration.toMinutes()/60.0)) ;

        return dayCharge+nightCharge ;
    }
    public Duration dayDuration(LocalDateTime entryTime, LocalDateTime exitTime){
        Duration dayDuration = Duration.ZERO;
        LocalDate date = entryTime.toLocalDate();
        while(!date.isAfter(exitTime.toLocalDate())){
            LocalDateTime dayStart = LocalDateTime.of(date, LocalTime.of(6,0));
            LocalDateTime dayEnd = LocalDateTime.of(date, LocalTime.of(18,0));

            LocalDateTime start = entryTime.isBefore(dayStart) ? dayStart : entryTime;
            LocalDateTime end = exitTime.isAfter(dayEnd) ? dayEnd : exitTime;

            if(start.isBefore(end)){
                dayDuration = dayDuration.plus(Duration.between(start,end));
            }
            date=date.plusDays(1);
        }
        return dayDuration ;
    }
}
