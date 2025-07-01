package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.BookingDTO;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import com.example.MovieBookingApplication.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @GetMapping("/getuserbookings/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }

    @GetMapping("/getshowbookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getShowBookings(id));
    }

    @PutMapping("{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @PutMapping("{bookingId}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @GetMapping("/getbookingsbystatus/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status) {
        return ResponseEntity.ok(bookingService.getBookingByStatus(status));
    }

}
