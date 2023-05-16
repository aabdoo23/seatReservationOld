package com.example.seatreservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Slot {
    private LocalDateTime ltd;
    Slot(LocalDateTime ltd){
        this.ltd=ltd;
    }

    public void setLtd(LocalDateTime ltd) {
        this.ltd = ltd;
    }

    public LocalDateTime getLtd() {
        return ltd;
    }

    @Override
    public String toString() {
        return ltd.toLocalDate().toString()+" "+ltd.toLocalTime().toString();
    }
}
