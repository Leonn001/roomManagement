package com.project.spaceRequestService.services;

import com.project.spaceRequestService.client.SpaceServiceClient;
import com.project.spaceRequestService.models.dto.SpaceDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SpaceServiceImplementation implements SpaceServiceClient {

    @Value("${spaceService.url}")
    private String spaceServiceUrl;

    @Override
    public SpaceDTO getSpaceByUsername(String username) {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(spaceServiceUrl+"/api/space-service/getSpaceByUsername/{username}", username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(SpaceDTO.class);
    }
}
