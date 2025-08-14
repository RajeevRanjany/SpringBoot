package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.TheaterDTO;
import com.example.MovieBookingApplication.entity.Theater;
import com.example.MovieBookingApplication.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @Tag(name = "Theater APIs",description = "APIs for Managing Theater")
    @Operation(summary = "Add Theater",description = "ADMIN APIs for Adding Theater")
    @PostMapping("/addtheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterDTO theaterDTO) {
        return ResponseEntity.ok(theaterService.addTheater(theaterDTO));

    }

    @Tag(name = "Theater APIs",description = "APIs for Managing Theater")
    @Operation(summary = "Get Theater by Location",description = "APIs for Getting Theater by Location")
    @GetMapping("/gettheaterbylocation")
    public ResponseEntity<List<Theater>> getTheaterByLocation(@RequestParam String location) {
        return ResponseEntity.ok(theaterService.getTheaterByLocation(location));
    }

    @Tag(name = "Theater APIs",description = "APIs for Managing Theater")
    @Operation(summary = "Update Theater",description = "ADMIN APIs for updating Theater Information")
    @PutMapping("/updatetheater")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody TheaterDTO theaterDTO) {
        return ResponseEntity.ok(theaterService.updateTheater(id, theaterDTO));
    }

    @Tag(name = "Theater APIs",description = "APIs for Managing Theater")
    @Operation(summary = "Delete Theater",description = "ADMIN APIs for Deleting Theater By Id")
    @DeleteMapping("/deletetheater/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.ok().build();
    }



}
