package com.arrKhange1.file_service.entity;

import com.arrKhange1.file_service.type.FileSystemNodeType;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "fs")
public class FileSystemNode {
    @Id
    private ObjectId id;

    private ObjectId parentId;

    private String name;

    private String type;
}
