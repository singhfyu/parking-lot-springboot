package com.singhfyu.parkinglot.domain;

import com.singhfyu.parkinglot.repository.ParkingSpotRepository;
import com.singhfyu.parkinglot.repository.TicketRepository;

import java.time.LocalDateTime;

public class ParkingLot {
    private PricingStrategy pricingStrategy;
    private TicketRepository ticketRepository;
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingLot( PricingStrategy pricingStrategy,
                      ParkingSpotRepository parkingSpotRepository, TicketRepository ticketRepository){
        this.pricingStrategy=pricingStrategy;
        this.ticketRepository=ticketRepository;
        this.parkingSpotRepository=parkingSpotRepository;
    }
    public long calculateFee(Ticket ticket, LocalDateTime exitTime){
        return pricingStrategy.calculateFee(ticket, exitTime);
    }
    public Ticket generateTicketAndParkVehicle(Vehicle vehicle) {

        ParkingSpot parkingSpot = findAndAllocateSpot(vehicle);
        if(parkingSpot==null){
            throw new IllegalStateException(" Parking spots are full !");
        }
        LocalDateTime entryTime = LocalDateTime.now() ;
        //generate ticket
        Ticket ticket = new Ticket(entryTime, vehicle, parkingSpot);
        return ticketRepository.save(ticket); //persist ticket in database
    }

    public long unparkVehicleAndReturnFee(Vehicle vehicle, LocalDateTime exitTime){

        Ticket ticket = (ticketRepository.findByVehicleVehicleNumberAndExitTimeIsNull(vehicle.getVehicleNumber()))
                .orElseThrow(()-> new IllegalStateException("Vehicle is not currently parked"));
        if(exitTime.isBefore(ticket.getEntryTime())){
            throw new IllegalArgumentException("Exit time can't be before entry time.");
        }
        ticket.setExitTime(exitTime);
        ticketRepository.save(ticket); //saving changes made to ticket in database


        ParkingSpot spot = ticket.getParkingSpot();
        spot.setOccupied(false);
        parkingSpotRepository.save(spot);//saving changes made to parking spot of current vehicle
        long fee = calculateFee(ticket, exitTime);

        return fee;
    }
    public ParkingSpot findAndAllocateSpot(Vehicle vehicle){
        //searches appropriate spot according to vehicle type and allocates that spot to that vehicle
        SpotType requiredSpotType = vehicle.getVehicleType().getSpotType();

        for(ParkingSpot parkingSpot: parkingSpotRepository.findAll()){
            if(!parkingSpot.isOccupied() && parkingSpot.getSpotType()==requiredSpotType) {
                parkingSpot.setOccupied(true);
                return parkingSpotRepository.save(parkingSpot);
            }
        }
        return null;
    }
}
