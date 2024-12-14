package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.FileSystemNode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FileSystemNodeRepository extends MongoRepository<FileSystemNode, ObjectId> {
    List<FileSystemNode> findAllByParentId(ObjectId parentId);
}