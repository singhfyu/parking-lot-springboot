package com.singhfyu.parkinglot.repository;

import com.singhfyu.parkinglot.domain.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

}
