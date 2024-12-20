package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import org.bson.types.ObjectId;

public interface MongoTemplateDirectoryNodeRepository {
    void updateDirectoryFieldsFrom(DirectoryDoc directoryDoc, ObjectId directoryId);
}
