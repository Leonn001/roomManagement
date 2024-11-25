package com.project.userService.service;

import com.project.userService.config.TokenService;
import com.project.userService.model.dto.AuthenticationDTO;
import com.project.userService.model.dto.LoginResponseDTO;
import com.project.userService.model.dto.RegisterDTO;
import com.project.userService.model.dto.UserDTO;
import com.project.userService.model.entity.User;
import com.project.userService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity login(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity register(RegisterDTO data){
        System.out.println("Email in service: " + data.getEmail());
        if (this.userRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User user = User.builder()
                .username(data.getUsername())
                .email(data.getEmail())
                .password(encryptedPassword)
                .role(data.getRole())
                .build();

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        return userRepository.save(user);
    }

    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(user);
    }

    public void activateUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    public UserDTO getUserDTOByName(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

}
