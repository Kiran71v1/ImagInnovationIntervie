package com.employee.IncomeTaxservice.JWT.models;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDTO userDTO) {
        // Ensure roles are provided, otherwise set a default role
        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            userDTO.setRoles(Set.of("ROLE_USER")); // Default role
        }

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Create the User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setRoles(userDTO.getRoles());

        // Save the user to the database
        return userRepository.save(user);
    }

}
