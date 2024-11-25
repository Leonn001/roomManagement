package com.project.spaceRequestService.client;

import com.project.spaceRequestService.models.dto.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserServiceClient {
    @GetExchange("/api/user-service/getUserByUsername/{username}")
    UserDTO getUserByUsername(@PathVariable String username);
}