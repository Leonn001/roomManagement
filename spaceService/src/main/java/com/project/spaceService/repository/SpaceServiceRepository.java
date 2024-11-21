package com.project.spaceService.repository;

import com.project.spaceService.model.etity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceServiceRepository extends JpaRepository<Space, Long > {
}