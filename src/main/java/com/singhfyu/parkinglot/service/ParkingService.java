package com.singhfyu.parkinglot.service;

import com.singhfyu.parkinglot.domain.ParkingLot;
import com.singhfyu.parkinglot.domain.Ticket;
import com.singhfyu.parkinglot.domain.Vehicle;
import com.singhfyu.parkinglot.repository.ParkingSpotRepository;
import com.singhfyu.parkinglot.repository.TicketRepository;
import com.singhfyu.parkinglot.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ParkingService {
    private VehicleRepository vehicleRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private TicketRepository ticketRepository;
    private ParkingLot parkingLot;
    public ParkingService(VehicleRepository vehicleRepository, ParkingSpotRepository parkingSpotRepository,
                          TicketRepository ticketRepository, ParkingLot parkingLot){
        this.vehicleRepository=vehicleRepository;
        this.parkingSpotRepository=parkingSpotRepository;
        this.ticketRepository=ticketRepository;
        this.parkingLot=parkingLot;
    }

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }
    @Transactional
    public Ticket parkVehicle(Vehicle vehicle){
        //vehicle will be taken through input(POST method)
        if((ticketRepository.findByVehicleVehicleNumberAndExitTimeIsNull(vehicle.getVehicleNumber())).isPresent()){
            throw new IllegalStateException("Vehicle already parked !");
        }
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        Ticket ticket = parkingLot.generateTicketAndParkVehicle(savedVehicle);
        return ticket;
    }
    @Transactional
    public long unparkVehicleAndReturnFee(Vehicle vehicle){
        return parkingLot.unparkVehicleAndReturnFee(vehicle, LocalDateTime.now());
    }

}
