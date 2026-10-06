package com.singhfyu.parkinglot.domain;

import java.time.LocalDateTime;

public interface PricingStrategy {
    public long calculateFee(Ticket ticket, LocalDateTime exitTime);
}
