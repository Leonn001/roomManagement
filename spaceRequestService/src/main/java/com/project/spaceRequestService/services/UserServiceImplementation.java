package com.project.spaceRequestService.services;

import com.project.spaceRequestService.client.UserServiceClient;
import com.project.spaceRequestService.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserServiceImplementation implements UserServiceClient {

    @Value("${userService.url}")
    private String userServiceUrl;

    @Override
    public UserDTO getUserByUsername(String username) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(userServiceUrl+"/api/user-service/getUserByUsername/{username}", username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(UserDTO.class);

    }

}
