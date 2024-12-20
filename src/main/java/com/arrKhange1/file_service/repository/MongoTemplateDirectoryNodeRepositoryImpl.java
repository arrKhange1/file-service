package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.util.UpdateDocumentUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongoTemplateDirectoryNodeRepositoryImpl implements MongoTemplateDirectoryNodeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateDirectoryFieldsFrom(DirectoryDoc directoryDoc, ObjectId directoryId) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(directoryId));
        Update patch = UpdateDocumentUtil.preparePatchObjectForUpdateOperation(mongoTemplate.getConverter(), directoryDoc);
        mongoTemplate.updateFirst(query, patch, DirectoryDoc.class);
    }
}
