package com.singhfyu.parkinglot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HolidayPricingDecorator implements PricingStrategy{
    private PricingStrategy pricingStrategy;
    private HolidayCalendar holidayCalendar ;
    public HolidayPricingDecorator(PricingStrategy pricingStrategy, HolidayCalendar holidayCalendar){
        this.pricingStrategy=pricingStrategy;
        this.holidayCalendar=holidayCalendar;
    }

    @Override
    public long calculateFee(Ticket ticket, LocalDateTime exitTime) {
        long baseFee = pricingStrategy.calculateFee(ticket, exitTime);
        return containsHoliday(ticket, exitTime) ? (baseFee*2) : baseFee;
    }
    public boolean containsHoliday(Ticket ticket, LocalDateTime exitTime){

        LocalDate date = ticket.getEntryTime().toLocalDate();
        while(!date.isAfter(exitTime.toLocalDate())){
            if(holidayCalendar.isHoliday(date)){
                return true;
            }
            date = date.plusDays(1);
        }
        return false;
    }
}
