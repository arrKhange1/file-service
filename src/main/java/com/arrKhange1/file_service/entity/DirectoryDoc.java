package com.arrKhange1.file_service.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "fs")
public class DirectoryDoc extends FileSystemNode {
}