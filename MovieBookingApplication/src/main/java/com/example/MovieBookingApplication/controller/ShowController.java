package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.ShowDTO;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @Tag(name = "Show APIs",description = "APIs for Managing Shows")
    @Operation(summary = "Create Show",description = "APIs for creating shows")

    @PostMapping("/createshow")
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
        return ResponseEntity.ok(showService.createShow(showDTO));
    }

    @Tag(name = "Show APIs",description = "APIs for Managing Shows")
    @Operation(summary = "Get All Show",description = "APIs for getting all shows")
    @GetMapping("/getallshows")
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @Tag(name = "Show APIs",description = "APIs for Managing Shows")
    @Operation(summary = "Get Show by Movie Id",description = "APIs for getting shows by Movies")

    @GetMapping("/getshowsbymovie/{movieId}")
    public ResponseEntity<List<Show>> getShowByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }

    @Tag(name = "Show APIs",description = "APIs for Managing Shows")
    @Operation(summary = "Get Show by Theater",description = "APIs for Getting shows by theater")

    @GetMapping("/getshowsbytheater/{theaterId}")
    public ResponseEntity<List<Show>> getShowByTheater(@PathVariable Long theaterId) {
        return ResponseEntity.ok(showService.getShowsByTheater(theaterId));
    }

    @Tag(name = "Show APIs",description = "APIs for Managing Shows")
    @Operation(summary = "Update Show",description = "APIs for Updating shows")

    @PutMapping("/updateshow/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
        return ResponseEntity.ok(showService.updateShow(id, showDTO));

    }

    @Tag(name = "Show APIs",description = "APIs for Managing Shows")
    @Operation(summary = "Delete Show",description = "APIs for Deleting shows")

    @DeleteMapping("/deleteshow/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }

}
