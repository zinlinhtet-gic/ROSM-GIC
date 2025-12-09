package com.gic.rosm.Service;

import com.gic.rosm.DTOs.LoginReponse;
import com.gic.rosm.DTOs.LoginRequest;
import com.gic.rosm.DTOs.ProfileResponse;
import com.gic.rosm.Entity.Staff;
import com.gic.rosm.Exceptions.StaffNotFoundException;
import com.gic.rosm.Exceptions.UnAuthorizedAccessException;
import com.gic.rosm.Repository.StaffRepository;
import com.gic.rosm.Utility.JwtUtils;
import com.gic.rosm.Utility.Logger;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    private final BlackListedService blackListedService;
    private final StaffRepository staffRepository;
    private final JwtUtils jwtUtils;
    private final Logger logger = Logger.getInstance();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(BlackListedService blackListedService,StaffRepository staffRepository,JwtUtils jwtUtils) {
        //this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.staffRepository = staffRepository;
        this.blackListedService = blackListedService;
    }

    public LoginReponse login(LoginRequest loginRequest) throws StaffNotFoundException, UnAuthorizedAccessException {
        String login_name = loginRequest.getLoginName();
        String password = loginRequest.getPassword();
        Optional<Staff> existingStaff = staffRepository.findByLoginName(login_name);
        if (existingStaff.isEmpty()) {
            logger.error("AuthService","Login failed for login name: " +login_name);
            throw new StaffNotFoundException("Staff not found with login name: " + login_name);
        }
        Staff staff = existingStaff.get();
        if (!(passwordEncoder.matches(password,staff.getPassword()))) {
            logger.error("AuthService",staff.getPassword() +" : " + passwordEncoder.encode(password));
            logger.error("AuthService","Invalid password for login name: " +login_name);
            throw new UnAuthorizedAccessException("Invalid password for login name: " + login_name);
        }
        String token = jwtUtils.generateToken(staff);
        logger.info("AuthService","Login successful for login name: " + login_name);
        return new LoginReponse(staff.getLoginName(), staff.getRole().toString(),staff.getId(), token);
    }

    public String logout(String token) {
        // In a stateless JWT setup, logout can be handled on the client side by simply deleting the token.
        // Optionally, you can implement token blacklisting on the server side if needed.
        logger.info("AuthService","Logout requested for token: " + token);
        blackListedService.addTokenToBlacklist(token);
        return "Logout successful";
    }

    public ProfileResponse getProfile(Long id) throws StaffNotFoundException {

        Optional<Staff> existingStaff = staffRepository.findById(id);
        if (existingStaff.isEmpty()) {
            logger.error("AuthService","Profile retrieval failed for staff ID: " +id);
            throw new StaffNotFoundException("Staff not found with ID: " + id);
        }
        Staff staff = existingStaff.get();
        logger.info("AuthService","Profile retrieved successfully for staff ID: " + id);
        return new ProfileResponse( staff.getName(), staff.getRole().toString(),staff.getId());
    }

}
