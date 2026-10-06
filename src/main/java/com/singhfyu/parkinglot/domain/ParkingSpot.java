package com.singhfyu.parkinglot.domain;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    @Column(unique = true, nullable = false)
    private int spotId;

    @Column(nullable = false)
    private boolean occupied;

    protected ParkingSpot(){

    }
    public ParkingSpot(int spotId, SpotType spotType, boolean occupied){
        this.spotId=spotId;
        this.spotType=spotType;
        this.occupied=occupied;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public long getSpotId() {
        return spotId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public long getId() {
        return id;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
