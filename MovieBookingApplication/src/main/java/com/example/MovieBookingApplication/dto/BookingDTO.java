package com.example.MovieBookingApplication.dto;

import com.example.MovieBookingApplication.entity.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDTO {
    @Schema(name = "No of seats You want to book", example = "1/2/3")
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private double price;
    private BookingStatus bookingStatus;
    private List<String> seatsNumbers;
    private Long userId;
    private Long showId;
}
