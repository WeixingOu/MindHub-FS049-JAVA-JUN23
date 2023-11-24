package com.mindhub.crud.controllers;

import com.mindhub.crud.dtos.UserDTO;
import com.mindhub.crud.models.Users;
import com.mindhub.crud.services.UserService;
import com.mindhub.crud.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static com.mindhub.crud.utils.UserUtils.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/users/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<UserDTO>> updateUser (@PathVariable Long id, @RequestBody UserDTO userDTO, Authentication authentication) {
        if (userService.getUserByEmail(authentication.getName()) == null) {
            UserDTO errorUserDto = new UserDTO();
            errorUserDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorUserDto));
        }

        Users currentUser = userService.getUserById(id);

        if (currentUser == null) {
            UserDTO errorUserDto = new UserDTO();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("User not found", errorUserDto));
        }

        if (!validateUserFields(userDTO)) {
            UserDTO errorUserDto = new UserDTO();

            errorUserDto.setFirstName(userDTO.getFirstName());
            errorUserDto.setLastName(userDTO.getLastName());
            errorUserDto.setEmail(userDTO.getEmail());
            errorUserDto.setBirthdate(userDTO.getBirthdate());
            errorUserDto.setGender(userDTO.getGender());
            errorUserDto.setRole(userDTO.getRole());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid input data", errorUserDto));
        }

        if (!regExpEmailValidation(userDTO.getEmail())) {
            UserDTO errorUserDto = new UserDTO();
            errorUserDto.setEmail(userDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid email format", errorUserDto));
        }

        if (!userDTO.getEmail().equals(currentUser.getEmail()) && userService.existsByEmail(userDTO.getEmail())) {
            UserDTO errorUserDto = new UserDTO();
            errorUserDto.setEmail(userDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Email already in use", errorUserDto));
        }

        int age = Period.between(userDTO.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18) {
            UserDTO errorUserDto = new UserDTO();
            errorUserDto.setBirthdate(userDTO.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Age must be at least 18 years old", errorUserDto));
        }

        if (userDTO.getBirthdate().isAfter(LocalDate.now())) {
            UserDTO errorUserDto = new UserDTO();
            errorUserDto.setBirthdate(userDTO.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Birthdate cannot be in the future", errorUserDto));
        }

        currentUser.setFirstName(userDTO.getFirstName());
        currentUser.setLastName(userDTO.getLastName());
        currentUser.setEmail(userDTO.getEmail());
        currentUser.setBirthdate(userDTO.getBirthdate());
        currentUser.setGender(userDTO.getGender());
        currentUser.setRole(userDTO.getRole());
        currentUser.setActive(userDTO.isActive());

        UserDTO uptUser = userService.updateUser(currentUser);

        return ResponseEntity.ok(new ApiResponse<>("User updated successfully", uptUser));
    }
}
