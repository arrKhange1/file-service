package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.FileDoc;
import org.bson.types.ObjectId;

public interface MongoTemplateFileNodeRepository {
    void updateFileFieldsFrom(FileDoc fileDoc, ObjectId fileId);
}
