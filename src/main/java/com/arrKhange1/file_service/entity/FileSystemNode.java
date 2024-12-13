package com.arrKhange1.file_service.entity;

import com.arrKhange1.file_service.type.FileSystemNodeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collation = "fs")
public abstract class FileSystemNode {
    @Id
    private ObjectId id;

    private ObjectId parentId;

    private String name;

    @Enumerated(EnumType.STRING)
    private FileSystemNodeType type;
}
