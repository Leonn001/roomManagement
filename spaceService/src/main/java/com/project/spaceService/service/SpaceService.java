package com.project.spaceService.service;

import com.project.spaceService.model.dto.SpaceDTO;
import com.project.spaceService.model.dto.SpaceServiceRequestDTO;
import com.project.spaceService.model.etity.Space;
import com.project.spaceService.repository.SpaceServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {

    @Autowired
    private SpaceServiceRepository spaceServiceRepository;

    public Space createSpace(SpaceServiceRequestDTO data) {
        Space space = Space.builder()
                .name(data.getName())
                .location(data.getLocation())
                .capacity(data.getCapacity())
                .type(data.getType())
                .isAvailable(true)
                .isActive(true)
                .resources(data.getResources())
                .build();

        return spaceServiceRepository.save(space);
    }

    public void deleteSpace(Long id) {
        Optional<Space> space = spaceServiceRepository.findById(id);

        if (space.isPresent()) {
            spaceServiceRepository.delete(space.get());
        } else {
            throw new RuntimeException("Space not found");
        }
    }

    public Space updateSpace(Long id, SpaceServiceRequestDTO data) {
        Space space = spaceServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Space not found"));

        if (data.getName() != null && !data.getName().isEmpty()) {
            space.setName(data.getName());
        }
        space.setLocation(data.getLocation());
        space.setCapacity(data.getCapacity());
        space.setType(data.getType());
        space.setResources(data.getResources());

        return spaceServiceRepository.save(space);
    }

    public List<Space> getAllSpaces() {
        return spaceServiceRepository.findAll();
    }

    public Optional<Space> getSpaceById(Long spaceId) {
        return spaceServiceRepository.findById(spaceId);
    }

    public Boolean existsById(Long userId) {
        return spaceServiceRepository.existsById(userId);
    }

    public SpaceDTO findSpaceDTOByName(String username) {
        return spaceServiceRepository.findSpaceDTOByName(username);
    }

}