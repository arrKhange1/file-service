package com.arrKhange1.file_service.dto;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public record FileMutationRequestDTO(
     @NotBlank(message = "Filename is mandatory", groups = AddValidation.class)
     String name,
     String description,
     ObjectId parentId
)  {
     public interface AddValidation {}
     public interface PatchValidation {}
}
