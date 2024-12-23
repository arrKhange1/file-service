package com.arrKhange1.file_service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Data
@Document(collection = "fs")
public class FileDoc extends FileSystemNode {
    private String description;
}