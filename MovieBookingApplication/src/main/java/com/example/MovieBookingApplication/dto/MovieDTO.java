package com.example.MovieBookingApplication.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private String name;
    private String description;
    private String genre;
    private Integer Duration;
    private LocalDate releaseDate;
    private String language;

}
