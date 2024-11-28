package com.project.spaceRequestService.controller;

import com.project.spaceRequestService.models.dto.SpaceRequestDTO;
import com.project.spaceRequestService.models.entity.SpaceRequest;
import com.project.spaceRequestService.service.SpaceRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/space-request")
@Tag(name = "Space Request Service", description = "Endpoints relacionados às requisições dos spaces")
public class SpaceRequestController {

    @Autowired
    private SpaceRequestService spaceRequestService;

    @PostMapping("/createSpaceRequest")
    @Operation(summary = "Criar uma requisição à um space", description = "Criar uma requisição para um space do sistema")
    public ResponseEntity<SpaceRequest> createSpaceRequest(@RequestBody SpaceRequestDTO spaceRequestDTO) {
        SpaceRequest createdSpaceRequest = spaceRequestService.createSpaceRequest(spaceRequestDTO);
        return new ResponseEntity<>(createdSpaceRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/accept")
    @Operation(summary = "Aceitar uma requisição à um space", description = "Aceita uma requisição para um space do sistema")
    public ResponseEntity<SpaceRequest> acceptRequest(@PathVariable Long id) {
        SpaceRequest acceptedRequest = spaceRequestService.acceptSpaceRequest(id);
        return ResponseEntity.ok(acceptedRequest);
    }

    @PutMapping("/{id}/decline")
    @Operation(summary = "Recusar uma requisição à um space", description = "Recusar uma requisição para um space do sistema")
    public ResponseEntity<SpaceRequest> declineRequest(@PathVariable Long id) {
        SpaceRequest declinedRequest = spaceRequestService.declineSpaceRequest(id);
        return ResponseEntity.ok(declinedRequest);
    }

    @GetMapping("/getAllSpaceRequests")
    @Operation(summary = "Lista todas as requisições de espaço", description = "Lista todas as reqs. de espaço")
    public ResponseEntity<List<SpaceRequest>> getAllSpaceRequests() {
        List<SpaceRequest> spaceRequests = spaceRequestService.getAllSpaceRequests();
        return ResponseEntity.ok(spaceRequests);
    }

}

