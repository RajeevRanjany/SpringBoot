package com.example.MovieBookingApplication.service;

import com.example.MovieBookingApplication.dto.ShowDTO;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.Movie;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.entity.Theater;
import com.example.MovieBookingApplication.repository.MovieRepository;
import com.example.MovieBookingApplication.repository.ShowRepository;
import com.example.MovieBookingApplication.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public Show createShow(ShowDTO showDTO) {

        Movie movie = movieRepository.findById(showDTO.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found for id: " + showDTO.getMovieId()));

        Theater theater = theaterRepository.findById(showDTO.getTheaterId()).orElseThrow(() -> new RuntimeException("Theater not found for id: " + showDTO.getTheaterId()));

        Show show = new Show();
        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);
        showRepository.save(show);
        return show;

    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public List<Show> getShowsByMovie(Long movieId) {
        Optional<List<Show>> show = showRepository.findByMovieId(movieId);
        if (show.isPresent()) {
            return show.get();
        } else throw new RuntimeException("No show available for the movie " + movieId);
    }

    public List<Show> getShowsByTheater(Long theaterId) {
        Optional<List<Show>> showList = showRepository.findByTheaterId(theaterId);
        if (showList.isPresent()) {
            return showList.get();
        } else throw new RuntimeException("No show available In the theater " + theaterId);
    }

    public Show updateShow(Long id, ShowDTO showDTO) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found for id: " + id));

        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found for id: " + showDTO.getMovieId()));
        Theater theater = theaterRepository.findById(showDTO.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found for id: " + showDTO.getTheaterId()));

        show.setShowTime(showDTO.getShowTime());
        show.setPrice(showDTO.getPrice());
        show.setMovie(movie);
        show.setTheater(theater);

        showRepository.save(show);
        return show;
    }

    public void deleteShow(Long id) {
       if(!showRepository.existsById(id)) {
           throw new RuntimeException("No Show available for the id: " + id);
       }
       List<Booking> bookings = showRepository.findById(id).get().getBookings();
       if(!bookings.isEmpty()) {
          throw new RuntimeException("Can't delete shows with existing booking");
       }
       showRepository.deleteById(id);

    }
}
