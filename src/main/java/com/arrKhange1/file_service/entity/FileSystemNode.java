package com.arrKhange1.file_service.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@FieldNameConstants
@NoArgsConstructor
@SuperBuilder
@Data
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
