package com.arrKhange1.file_service.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@FieldNameConstants
@Getter
@Setter
@Document(collection = "fs")
public class FileSystemNode {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId parentId;

    private String name;

    private String type;
}
