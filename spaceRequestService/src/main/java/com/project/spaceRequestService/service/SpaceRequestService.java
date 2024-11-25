package com.project.spaceRequestService.service;

import com.project.spaceRequestService.client.SpaceServiceClient;
import com.project.spaceRequestService.client.UserServiceClient;
import com.project.spaceRequestService.models.dto.SpaceDTO;
import com.project.spaceRequestService.models.dto.SpaceRequestDTO;
import com.project.spaceRequestService.models.dto.UserDTO;
import com.project.spaceRequestService.models.entity.SpaceRequest;
import com.project.spaceRequestService.models.enums.Status;
import com.project.spaceRequestService.repository.SpaceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SpaceRequestService {
    @Autowired
    private  UserServiceClient userServiceClient;
    @Autowired
    private  SpaceServiceClient spaceServiceClient;
    @Autowired
    SpaceRequestRepository spaceRequestRepository;

    public SpaceRequest createSpaceRequest(SpaceRequestDTO data) {
        UserDTO user = userServiceClient.getUserByUsername(data.getUserName());
        SpaceDTO space = spaceServiceClient.getSpaceByUsername(data.getSpaceName());


        if (data.getStartDate().isAfter(data.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
        if (user == null) {
            throw new RuntimeException("user with name " + user.getUsername() + " does not exist");
        }
        if (space == null) {
            throw new RuntimeException("space with name " + space.getName() + " does not exist");
        }

        SpaceRequest spaceRequest = SpaceRequest.builder()
                .requestDate(LocalDateTime.now())
                .userName(user.getUsername())
                .spaceName(space.getName())
                .startDate(data.getStartDate())
                .endDate(data.getEndDate())
                .status(Status.PENDENTE)
                .build();

        return spaceRequestRepository.save(spaceRequest);
    }

    public SpaceRequest acceptSpaceRequest(Long requestId) {
        Optional<SpaceRequest> optionalRequest = spaceRequestRepository.findById(requestId);

        if (optionalRequest.isEmpty()) {
            throw new RuntimeException("Space request with ID " + requestId + " not found");
        }

        SpaceRequest spaceRequest = optionalRequest.get();

        if (spaceRequest.getStatus() != Status.PENDENTE) {
            throw new IllegalStateException("Only pending requests can be accepted");
        }

        spaceRequest.setStatus(Status.APROVADO);
        return spaceRequestRepository.save(spaceRequest);
    }

    public SpaceRequest declineSpaceRequest(Long requestId) {
        Optional<SpaceRequest> optionalRequest = spaceRequestRepository.findById(requestId);

        if (optionalRequest.isEmpty()) {
            throw new RuntimeException("Space request with ID " + requestId + " not found");
        }

        SpaceRequest spaceRequest = optionalRequest.get();

        if (spaceRequest.getStatus() != Status.PENDENTE) {
            throw new IllegalStateException("Only pending requests can be declined");
        }

        spaceRequest.setStatus(Status.REJEITADO);
        return spaceRequestRepository.save(spaceRequest);
    }

}
