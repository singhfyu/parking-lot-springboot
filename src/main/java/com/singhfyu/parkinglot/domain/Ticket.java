package com.singhfyu.parkinglot.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    @ManyToOne
    private  Vehicle vehicle;

    @ManyToOne
    private ParkingSpot parkingSpot;

    protected Ticket(){

    }
    public Ticket(LocalDateTime entryTime, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.entryTime=entryTime;
        this.vehicle=vehicle;
        this.parkingSpot=parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setExitTime(LocalDateTime exitTime){
        this.exitTime=exitTime;
    }

    public Long getId() {
        return id;
    }
}
