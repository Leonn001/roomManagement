package com.project.spaceService.model.dto;

import com.project.spaceService.model.enums.SpaceType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SpaceServiceRequestDTO {
    private String name;
    private String location;
    private Integer capacity;
    private SpaceType type;
    private List<String> resources;
}