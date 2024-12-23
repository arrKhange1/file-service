package com.arrKhange1.file_service.dto;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public record DirectoryMutationRequestDTO(
        @NotBlank(message = "Directory name is mandatory", groups = AddValidation.class)
        String name,
        ObjectId parentId
)  {
    public interface AddValidation {}
}
