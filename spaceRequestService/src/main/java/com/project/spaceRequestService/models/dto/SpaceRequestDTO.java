package com.project.spaceRequestService.models.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequestDTO {
    private String userName;
    private String spaceName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}