package com.project.spaceService.controller;

import com.project.spaceService.model.dto.SpaceServiceRequestDTO;
import com.project.spaceService.model.etity.Space;
import com.project.spaceService.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-SpaceService")
public class SpaceServiceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/createSpace")
    public ResponseEntity<Space> createSpace(@RequestBody SpaceServiceRequestDTO spaceServiceRequestDTO) {
        Space createdSpace = spaceService.createSpace(spaceServiceRequestDTO);
        return ResponseEntity.ok(createdSpace);
    }

    @DeleteMapping("/deleteSpace/{id}")
    public ResponseEntity<Space> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateSpace/{id}")
    public ResponseEntity<Space> updateSpace(@PathVariable Long id, @RequestBody SpaceServiceRequestDTO spaceServiceRequestDTO) {
        Space updatedSpace = spaceService.updateSpace(id, spaceServiceRequestDTO);
        return ResponseEntity.ok(updatedSpace);
    }

    @GetMapping("/getAllSpaces")
    public ResponseEntity<List<Space>> getAllSpaces() {
        List<Space> spaces = spaceService.getAllSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("getSpaceById/{spaceId}")
    public ResponseEntity<Space> getSpaceById(@PathVariable Long spaceId) {
        Optional<Space> space = spaceService.getSpaceById(spaceId);
        return space.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}