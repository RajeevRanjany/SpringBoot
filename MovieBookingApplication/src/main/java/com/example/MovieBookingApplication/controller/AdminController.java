package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.RegisterRequestDTO;
import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RestController
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @Tag(name = "Auth APIs",description = "APIs for managing User")
    @Operation(summary = "Register Admin User",description = "APIs for Registering Admin User")
    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
    }

}
