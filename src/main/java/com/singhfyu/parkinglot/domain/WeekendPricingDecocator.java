package com.singhfyu.parkinglot.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeekendPricingDecocator implements PricingStrategy{
    private PricingStrategy pricingStrategy;

    public WeekendPricingDecocator(PricingStrategy pricingStrategy){
        this.pricingStrategy=pricingStrategy;
    }

    @Override
    public long calculateFee(Ticket ticket, LocalDateTime exitTime) {
        long baseFee = pricingStrategy.calculateFee(ticket, exitTime);
        return containsWeekend(ticket, exitTime) ? (long)(baseFee*1.5) : baseFee;
    }

    public boolean containsWeekend(Ticket ticket, LocalDateTime exitTime){
        LocalDate date = ticket.getEntryTime().toLocalDate();
        while(!date.isAfter(exitTime.toLocalDate())){
            if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
                return true;
            }
            date = date.plusDays(1);
        }
        return false;
    }
}
