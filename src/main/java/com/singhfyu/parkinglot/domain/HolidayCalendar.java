package com.singhfyu.parkinglot.domain;

import java.time.LocalDate;
import java.util.Set;

public class HolidayCalendar {
    private Set<LocalDate> holidays = Set.of(
            LocalDate.of(2026, 3, 23),
            LocalDate.of(2026, 8, 15),
            LocalDate.of(2026, 10, 2),
            LocalDate.of(2026, 10, 5),
            LocalDate.of(2026, 12, 25)
    );
    public boolean isHoliday(LocalDate date){
        return holidays.contains(date);

    }
}
