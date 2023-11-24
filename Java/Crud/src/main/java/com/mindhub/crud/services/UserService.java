package com.mindhub.crud.services;

import com.mindhub.crud.dtos.UserDTO;
import com.mindhub.crud.models.Users;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    Boolean existsById(Long id);
    boolean existsByEmail(String email);
    Users getUserById(Long id);
    Users getUserByEmail(String email);
    UserDTO updateUser(Users user);
}
