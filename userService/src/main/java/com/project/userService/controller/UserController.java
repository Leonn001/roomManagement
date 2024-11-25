package com.project.userService.controller;

import com.project.userService.model.dto.AuthenticationDTO;
import com.project.userService.model.dto.LoginResponseDTO;
import com.project.userService.model.dto.RegisterDTO;
import com.project.userService.model.dto.UserDTO;
import com.project.userService.model.entity.User;
import com.project.userService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-service")
@Tag(name = "User Service", description = "Endpoints relacionados aos usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Realizar um login", description = "Logar no sistema, retorna um JWT")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        return userService.login(data);
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário", description = "Adiciona um novo usuário ao sistema")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        System.out.println("Received controller email: " + data.getEmail());
        return userService.register(data);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário", description = "Atualiza um usuário no sistema")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/deactivate/{id}")
    @Operation(summary = "Desativar um usuário", description = "Muda o atributo ativado para true")
    public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok("User deactivated successfully");
    }

    @PostMapping("/activate/{id}")
    @Operation(summary = "Ativar um usuário", description = "Muda o atributo ativado para false")
    public ResponseEntity<?> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok("User activated successfully");
    }

    @GetMapping("/getAllUsers")
    @Operation(summary = "Listar todos os usuário", description = "Retorna uma lista com todos os usuários registrados")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUserById/{id}")
    @Operation(summary = "Buscar um usuário pelo ID", description = "Retorna, se existir, um usuário com base em seu ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("existsById/{id}")
    @Operation(summary = "Verificar a existencia de um usuario pelo id", description = "Verifica se o usuário existe com base em seu id")
    public Boolean existsById(@PathVariable Long id) {
        return userService.existsById(id);
    }

    @GetMapping("/getUserByUsername/{username}")
    @Operation(summary = "Buscar um usuário pelo nome", description = "Retorna, se existir, um usuário com base no seu nome")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.getUserDTOByName(username);
        return ResponseEntity.ok(userDTO);
    }
}