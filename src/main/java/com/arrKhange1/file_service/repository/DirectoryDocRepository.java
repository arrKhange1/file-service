package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DirectoryDocRepository extends
        MongoRepository<DirectoryDoc, ObjectId>,
        MongoTemplateDirectoryNodeRepository {
}