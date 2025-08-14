package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.MovieDTO;
import com.example.MovieBookingApplication.entity.Movie;
import com.example.MovieBookingApplication.service.MovieServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieServices movieServices;

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "Add Movie",description = "ADMIN APIs for Adding new Movie")
    @PostMapping("/addmovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieServices.addMovie(movieDTO));
    }

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "get Movie",description = "APIs for listing all Movies")
    @GetMapping("/getallmovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieServices.getAllMovies());
    }

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "get Movie by Genre",description = "APIs for listing all Movies by genre")
    @GetMapping("/getmoviesbygenre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(movieServices.getMovieByGenre(genre));
    }

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "Get Movies by Language", description = "APIs for listing Movies by language")
    @GetMapping("/getmoviesbylanguage")
    public ResponseEntity<List<Movie>> getMoviesByLanguage(@RequestParam String language) {
        return ResponseEntity.ok(movieServices.getMovieByLanguage(language));
    }

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "get Movie By Title",description = "APIs for listing Movies by title")
    @GetMapping("/getmoviebytitle")
    public ResponseEntity<Movie> getMovieByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieServices.getMovieByTitle(title));
    }

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "Update Movie",description = "ADMIN APIs for Updating Movie Information")
    @PutMapping("/updatemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        return movieServices.updateMovie(id, movieDTO);
    }

    @Tag(name = "Movie APIs",description = "APIs for Managing Movies")
    @Operation(summary = "Delete Movie",description = "ADMIN APIs for deleting a Movie")
    @DeleteMapping("/deletemovie/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieServices.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

}
