package com.arrKhange1.file_service.dto;

import org.bson.types.ObjectId;

public record DirectoryMutationRequestDTO(
        String name,
        ObjectId parentId
)  {
}
