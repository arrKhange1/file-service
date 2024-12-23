package com.arrKhange1.file_service.dto;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public class AddFileRequestDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    private ObjectId parentId;
}
