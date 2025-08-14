package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.BookingDTO;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import com.example.MovieBookingApplication.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Tag(name = "Booking APIs",description = "APIs for Managing Bookings")
    @Operation(summary = "Create Booking",description = "APIs for create Booking")
    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @Tag(name = "Booking APIs",description = "APIs for Managing Bookings")
    @Operation(summary = "Get User Bookings",description = "APIs for get Bookings")
    @GetMapping("/getuserbookings/{id}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }

    @Tag(name = "Booking APIs",description = "APIs for Managing Bookings")
    @Operation(summary = "get Show Booking",description = "APIs for getting Booked show")
    @GetMapping("/getshowbookings/{id}")
    public ResponseEntity<List<Booking>> getShowBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getShowBookings(id));
    }

    @Tag(name = "Booking APIs",description = "APIs for Managing Bookings")
    @Operation(summary = "Confirm Booking",description = "APIs for Confirm Booking")
    @PutMapping("{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.confirmBooking(id));
    }

    @Tag(name = "Booking APIs",description = "APIs for Managing Bookings")
    @Operation(summary = "Cancel Booking",description = "APIs for cancel Booking")
    @PutMapping("{bookingId}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }

    @Tag(name = "Booking APIs",description = "APIs for Managing Bookings")
    @Operation(summary = "Get Booking Status",description = "APIs for Booking status")
    @GetMapping("/getbookingsbystatus/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status) {
        return ResponseEntity.ok(bookingService.getBookingByStatus(status));
    }

}
