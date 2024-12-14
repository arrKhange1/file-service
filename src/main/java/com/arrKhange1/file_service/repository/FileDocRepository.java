package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.FileDoc;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileDocRepository extends MongoRepository<FileDoc, ObjectId> {
}