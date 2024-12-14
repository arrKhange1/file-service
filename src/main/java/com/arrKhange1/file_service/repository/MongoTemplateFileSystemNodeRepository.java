package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.FileSystemNode;
import org.bson.types.ObjectId;

import java.util.List;

public interface MongoTemplateFileSystemNodeRepository {
    List<FileSystemNode> deleteNodeByIdRecursive(ObjectId nodeId);
}