package com.project.spaceService.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpaceDTO {
    private String name;
    private Boolean isAvailable;
    private Boolean isActive;
}