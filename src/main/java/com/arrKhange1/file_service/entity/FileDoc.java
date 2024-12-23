package com.arrKhange1.file_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "fs")
public class FileDoc extends FileSystemNode {
    private String description;
}