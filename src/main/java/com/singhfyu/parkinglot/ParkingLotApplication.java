package com.singhfyu.parkinglot;

import com.singhfyu.parkinglot.domain.Ticket;
import com.singhfyu.parkinglot.domain.Vehicle;
import com.singhfyu.parkinglot.domain.VehicleType;
import com.singhfyu.parkinglot.repository.VehicleRepository;
import com.singhfyu.parkinglot.service.ParkingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParkingLotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

}
