package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.BookingDTO;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.repository.BookingRepository;
import com.example.MovieBookingApplication.repository.ShowRepository;
import com.example.MovieBookingApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(BookingDTO bookingDTO) {
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        if (!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())) {
            throw new RuntimeException("Not enough seats are available");
        }
        if (bookingDTO.getSeatsNumbers().size() != bookingDTO.getNumberOfSeats()) {
            throw new RuntimeException("Seats numbers & Number of seats must be equal");
        }
        validateDuplicateSeats(show.getId(), bookingDTO.getSeatsNumbers());

        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setShow(show);
        booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
        booking.setSeatsNumbers(bookingDTO.getSeatsNumbers());
        booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);

    }

    public double calculateTotalAmount(Double price, Integer numberOfSeats) {
        return price * numberOfSeats;
    }

    public void validateDuplicateSeats(Long showId, List<String> seatsNumbers) {

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        Set<String> occupiedSeat = show.getBookings().stream()
                .filter(b -> b.getBookingStatus() != BookingStatus.CANCELLED)
                .flatMap(b -> b.getSeatsNumbers().stream())
                .collect(Collectors.toSet());
        List<String> duplicateSeat = seatsNumbers.stream()
                .filter(occupiedSeat::contains).toList();
        if (!duplicateSeat.isEmpty()) {
            throw new RuntimeException("Seats are already booked");
        }


    }

    public boolean isSeatsAvailable(Long showId, Integer numberOfSeats) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
        int bookedSeats = show.getBookings().stream()
                .filter(booking -> booking.getBookingStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getNumberOfSeats)
                .sum();
        return show.getTheater().getTheaterCapacity() - bookedSeats >= numberOfSeats;
    }

    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getShowBookings(Long showId) {
        return bookingRepository.findByShowId(showId);
    }

    public Booking confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        if (!booking.getBookingStatus().equals(BookingStatus.PENDING)) {
            throw new RuntimeException("Booking status must be PENDING");
        }
//        Payment api
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        validateCancellation(booking);
        booking.setBookingStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    public void validateCancellation(Booking booking) {

        LocalDateTime showTime = booking.getShow().getShowTime();
        LocalDateTime deadline = showTime.minusHours(2);

        if (LocalDateTime.now().isAfter(deadline)) {
            throw new RuntimeException("Cannot cancel booking before starting show time is less than 2 hours");
        }
        if (booking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
            throw new RuntimeException("Booking is already been cancelled");
        }

    }

    public List<Booking> getBookingByStatus(BookingStatus status) {
        return bookingRepository.findByBookingStatus(status);
    }
}
