package com.project.spaceRequestService.repository;

import com.project.spaceRequestService.models.entity.SpaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRequestRepository extends JpaRepository<SpaceRequest, Long> {

}
