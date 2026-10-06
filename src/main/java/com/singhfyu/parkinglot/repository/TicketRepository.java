package com.singhfyu.parkinglot.repository;

import com.singhfyu.parkinglot.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByVehicleVehicleNumberAndExitTimeIsNull(String vehicleNumber);
}
