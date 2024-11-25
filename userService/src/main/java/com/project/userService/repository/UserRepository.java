package com.project.userService.repository;

import com.project.userService.model.dto.UserDTO;
import com.project.userService.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
    boolean existsById(Long id);
    UserDTO findUserDTOByUsername(String username);
}