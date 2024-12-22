package com.arrKhange1.file_service.repository;

import com.arrKhange1.file_service.entity.FileSystemNode;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class MongoTemplateFileSystemNodeRepositoryImpl implements MongoTemplateFileSystemNodeRepository {
    @Lazy
    @Autowired
    private FileSystemNodeRepository fileSystemNodeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void deleteNodeByIdRecursive(ObjectId nodeId) {
        var graphLookUp = Aggregation.graphLookup("fs")
                .startWith("parentId")
                .connectFrom("parentId")
                .connectTo("_id")
                .as("hierarchy");
        var finalAggregation = Aggregation.newAggregation(
                graphLookUp,
                Aggregation.match(new Criteria().orOperator(
                        Criteria.where("hierarchy._id").is(nodeId),
                        Criteria.where("_id").is(nodeId)
                ))
        );

        List<FileSystemNode> nodesToDelete =
                mongoTemplate.aggregate(finalAggregation, "fs", FileSystemNode.class).getMappedResults();

        fileSystemNodeRepository.deleteAll(nodesToDelete);
    }
}
