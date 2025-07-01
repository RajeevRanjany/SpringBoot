package com.example.MovieBookingApplication.repository;

import com.example.MovieBookingApplication.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    Optional<List<Theater>> findByTheaterLocation(String location);

}
