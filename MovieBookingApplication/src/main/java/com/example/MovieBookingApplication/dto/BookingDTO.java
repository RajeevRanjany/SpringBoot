package com.example.MovieBookingApplication.dto;

import com.example.MovieBookingApplication.entity.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private double price;
    private BookingStatus bookingStatus;
    private List<String> seatsNumbers;
    private Long userId;
    private Long showId;
}
