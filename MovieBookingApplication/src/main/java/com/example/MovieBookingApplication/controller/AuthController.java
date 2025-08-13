package com.example.MovieBookingApplication.controller;

import com.example.MovieBookingApplication.dto.LoginRequestDTO;
import com.example.MovieBookingApplication.dto.LoginResponseDTO;
import com.example.MovieBookingApplication.dto.RegisterRequestDTO;
import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Tag(name = "Auth APIs",description = "APIs for managing User")
    @Operation(summary = "Register Normal User",description = "APIs for Registering Normal User")
    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
    }

    @Tag(name = "Auth APIs",description = "APIs for managing User")
    @Operation(summary = "Login User",description = "APIs for Login User")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }
}
