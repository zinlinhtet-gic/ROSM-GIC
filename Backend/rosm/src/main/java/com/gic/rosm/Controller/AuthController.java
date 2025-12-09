package com.gic.rosm.Controller;

import com.gic.rosm.DTOs.LoginReponse;
import com.gic.rosm.DTOs.LoginRequest;
import com.gic.rosm.DTOs.ProfileResponse;
import com.gic.rosm.Exceptions.StaffNotFoundException;
import com.gic.rosm.Exceptions.UnAuthorizedAccessException;
import com.gic.rosm.Service.AuthService;
import com.gic.rosm.Utility.Logger;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final Logger logger = Logger.getInstance();
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try{
            this.logger.info("AuthController","Processing Login Request for user: " + loginRequest.getLoginName());
            LoginReponse loginReponse = authService.login(loginRequest);
            this.logger.info("AuthController","Login Successful for user: " + loginRequest.getLoginName());
            return ResponseEntity.ok(loginReponse);
        }
        catch (StaffNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        } catch (UnAuthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
        catch (Exception e)
        {
            this.logger.error("AuthController","Error during login for user: " + loginRequest.getLoginName() + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String response = authService.logout(token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header");
        }
    }

    @GetMapping("/me")
    public ResponseEntity me() {
        try{
            Long id = (Long)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            ProfileResponse profileResponse = authService.getProfile(id);
            return ResponseEntity.ok(profileResponse);
        }
        catch(StaffNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        catch (Exception e) {
            logger.error("AuthController",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
