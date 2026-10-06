package com.singhfyu.parkinglot.domain;

public enum VehicleType {
    BIKE(SpotType.COMPACT),
    CAR(SpotType.REGULAR),
    BUS(SpotType.OVERSIZE);
    private SpotType spotType;
    VehicleType(SpotType spotType){
        this.spotType = spotType;
    }
    public SpotType getSpotType(){
        return spotType;
    }
}
