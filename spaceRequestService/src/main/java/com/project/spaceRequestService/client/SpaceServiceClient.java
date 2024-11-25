package com.project.spaceRequestService.client;


import com.project.spaceRequestService.models.dto.SpaceDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface SpaceServiceClient {
    SpaceDTO getSpaceByUsername(@PathVariable String username);
}