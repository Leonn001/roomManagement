package com.project.spaceService.repository;

import com.project.spaceService.model.dto.SpaceDTO;
import com.project.spaceService.model.etity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceServiceRepository extends JpaRepository<Space, Long > {
    boolean existsById(Long id);
    SpaceDTO findSpaceDTOByName(String name);
}