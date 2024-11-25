package com.project.spaceRequestService.models.dto;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpaceDTO {
    private String name;
    private Boolean isAvailable;
    private Boolean isActive;
}
