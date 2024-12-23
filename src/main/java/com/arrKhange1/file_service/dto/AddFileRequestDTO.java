package com.arrKhange1.file_service.dto;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public record AddFileRequestDTO (
        @NotBlank(message = "Name is mandatory")
        String name,
        String description,
        ObjectId parentId) {
}
