package com.arrKhange1.file_service.repository;

import org.bson.types.ObjectId;

public interface MongoTemplateFileSystemNodeRepository {
    void deleteNodeByIdRecursive(ObjectId nodeId);
}