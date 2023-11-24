package com.mindhub.crud.utils;

import com.mindhub.crud.dtos.UserDTO;

import java.util.regex.Pattern;

public class UserUtils {
    private UserUtils() {}

    public static boolean regExpEmailValidation(String input) {
        return Pattern.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", input);
    }

    public static boolean regExpPassValidation(String input) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,15}$", input);
    }

    public static boolean validateUserFields(UserDTO userDTO) {
        return userDTO.getFirstName() != null && !userDTO.getFirstName().isBlank() &&
                userDTO.getLastName() != null && !userDTO.getLastName().isBlank() &&
                userDTO.getEmail() != null && !userDTO.getEmail().isBlank() &&
                userDTO.getBirthdate() != null &&
                userDTO.getGender() != null &&
                userDTO.getRole() != null;
    }
}
