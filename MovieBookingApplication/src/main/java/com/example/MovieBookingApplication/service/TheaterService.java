package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.TheaterDTO;
import com.example.MovieBookingApplication.entity.Theater;
import com.example.MovieBookingApplication.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;


    public Theater addTheater(TheaterDTO theaterDTO) {
        Theater theater = new Theater();
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public List<Theater> getTheaterByLocation(String location) {
        Optional<List<Theater>> theaterBox = theaterRepository.findByTheaterLocation(location);
        if (theaterBox.isPresent()) {
            return theaterBox.get();
        } else throw new RuntimeException("No such theater with location: " + location);

    }

    public Theater updateTheater(Long id, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such theater with id: " + id));
        theater.setTheaterName(theaterDTO.getTheaterName());
        theater.setTheaterLocation(theaterDTO.getTheaterLocation());
        theater.setTheaterCapacity(theaterDTO.getTheaterCapacity());
        theater.setTheaterScreenType(theaterDTO.getTheaterScreenType());
        return theaterRepository.save(theater);
    }

    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
