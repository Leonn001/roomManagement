package com.project.spaceService.controller;

import com.project.spaceService.model.dto.SpaceDTO;
import com.project.spaceService.model.dto.SpaceServiceRequestDTO;
import com.project.spaceService.model.etity.Space;
import com.project.spaceService.service.SpaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/space-service")
@Tag(name = "Space Service", description = "Endpoints relacionados aos spaces")
public class SpaceServiceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/createSpace")
    @Operation(summary = "Criar um space", description = "Criar um space no sistema")
    public ResponseEntity<Space> createSpace(@RequestBody SpaceServiceRequestDTO spaceServiceRequestDTO) {
        Space createdSpace = spaceService.createSpace(spaceServiceRequestDTO);
        return ResponseEntity.ok(createdSpace);
    }

    @DeleteMapping("/deleteSpace/{id}")
    @Operation(summary = "Deletar um space", description = "Deletar um space do sistema")
    public ResponseEntity<Space> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateSpace/{id}")
    @Operation(summary = "Atualizar um space", description = "Atualizar um space do sistema")
    public ResponseEntity<Space> updateSpace(@PathVariable Long id, @RequestBody SpaceServiceRequestDTO spaceServiceRequestDTO) {
        Space updatedSpace = spaceService.updateSpace(id, spaceServiceRequestDTO);
        return ResponseEntity.ok(updatedSpace);
    }

    @GetMapping("/getAllSpaces")
    @Operation(summary = "Listar todos os space", description = "Listar todos os spaces do sistema")
    public ResponseEntity<List<Space>> getAllSpaces() {
        List<Space> spaces = spaceService.getAllSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("getSpaceById/{spaceId}")
    @Operation(summary = "Buscar um space pelo ID", description = "Retorna, se existir, um space com base no seu id")
    public ResponseEntity<Space> getSpaceById(@PathVariable Long spaceId) {
        Optional<Space> space = spaceService.getSpaceById(spaceId);
        return space.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("existsById/{id}")
    @Operation(summary = "Verificar a existencia um space pelo ID", description = "Verifica se um space existe com base no seu id")
    public Boolean existsById(@PathVariable Long id) {
        return spaceService.existsById(id);
    }

    @GetMapping("getSpaceByUsername/{username}")
    @Operation(summary = "Buscar um space pelo name", description = "Retorna, se existir, um space com base no seu nome")
    public ResponseEntity<SpaceDTO> getSpaceByUsername(@PathVariable String username) {
        SpaceDTO spaceDTO = spaceService.findSpaceDTOByName(username);
        return ResponseEntity.ok(spaceDTO);
    }

}