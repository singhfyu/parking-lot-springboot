package com.singhfyu.parkinglot.config;

import com.singhfyu.parkinglot.domain.*;
import com.singhfyu.parkinglot.repository.ParkingSpotRepository;
import com.singhfyu.parkinglot.repository.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ParkingLotConfig {
    @Bean
    public PricingStrategy pricingStrategy(){
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        PricingStrategy basePrice = new DayNightPricingStrategy();
        return new HolidayPricingDecorator(new WeekendPricingDecocator(basePrice),holidayCalendar);
    }
    @Bean
    public List<ParkingSpot> initialiseParkingSpots(ParkingSpotRepository parkingSpotRepository){
        if(parkingSpotRepository.count()==0){
            parkingSpotRepository.save(new ParkingSpot(1,SpotType.COMPACT, false));
            parkingSpotRepository.save(new ParkingSpot(2,SpotType.COMPACT,false));
            parkingSpotRepository.save(new ParkingSpot(3,SpotType.REGULAR,false));
            parkingSpotRepository.save(new ParkingSpot(4,SpotType.REGULAR,false));
            parkingSpotRepository.save(new ParkingSpot(5,SpotType.OVERSIZE,false));
        }
        return parkingSpotRepository.findAll();
    }
    @Bean
    public ParkingLot parkingLot(PricingStrategy pricingStrategy
            ,ParkingSpotRepository parkingSpotRepository,TicketRepository ticketRepository ){
        return new ParkingLot(pricingStrategy, parkingSpotRepository, ticketRepository);
    }
}
