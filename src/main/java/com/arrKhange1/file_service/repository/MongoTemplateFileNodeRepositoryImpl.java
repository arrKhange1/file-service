package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.util.UpdateDocumentUtil;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongoTemplateFileNodeRepositoryImpl implements MongoTemplateFileNodeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateFileFieldsFrom(FileDoc fileDoc, ObjectId fileId) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(fileId));
        Update patch = UpdateDocumentUtil.preparePatchObjectForUpdateOperation(mongoTemplate.getConverter(), fileDoc);
        mongoTemplate.updateFirst(query, patch, FileDoc.class);
    }
}
