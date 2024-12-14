package com.arrKhange1.file_service.controller;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.repository.DirectoryDocRepository;
import com.arrKhange1.file_service.repository.FileDocRepository;
import com.arrKhange1.file_service.repository.FileSystemNodeRepository;
import com.arrKhange1.file_service.util.UpdateDocumentUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/fs-nodes")
public class FileSystemNodeController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FileSystemNodeRepository fileSystemNodeRepository;

    @Autowired
    private DirectoryDocRepository directoryDocRepository;

    @Autowired
    private FileDocRepository fileDocRepository;

    @PostMapping("file")
    public void addFile(@RequestBody FileDoc fileDoc) {
        if (Objects.equals(fileDoc.getType(), "file")) {
            fileDocRepository.save(fileDoc);
        }
    }

    @PostMapping("directory")
    public void addDirectory(@RequestBody() DirectoryDoc directoryDoc) {
        if (Objects.equals(directoryDoc.getType(), "dir")) {
            directoryDocRepository.save(directoryDoc);
        }
    }

    @PatchMapping("file/{fileId}")
    public void patchFile(@PathVariable("fileId") ObjectId fileId, @RequestBody FileDoc fileDoc) {
        Query query = new Query().addCriteria(Criteria.where("id").is(fileId));
        Document newFileDoc = new Document();
        mongoTemplate.getConverter().write(fileDoc, newFileDoc);
        Update upd = UpdateDocumentUtil.excludeNullFieldsFromDocuments(newFileDoc);
        mongoTemplate.updateFirst(query, upd, FileDoc.class);
    }

    @PatchMapping("directory/{directoryId}")
    public void patchFile(@PathVariable("directoryId") ObjectId directoryId, @RequestBody DirectoryDoc directoryDoc) {
        Query query = new Query().addCriteria(Criteria.where("id").is(directoryId));
        Document newDirectoryDoc = new Document();
        mongoTemplate.getConverter().write(directoryDoc, newDirectoryDoc);
        Update upd = UpdateDocumentUtil.excludeNullFieldsFromDocuments(newDirectoryDoc);
        mongoTemplate.updateFirst(query, upd, DirectoryDoc.class);
    }

    @DeleteMapping("{nodeId}")
    public List<FileSystemNode> deleteNode(@PathVariable("nodeId") ObjectId nodeId) {
        return fileSystemNodeRepository.deleteNodeByIdRecursive(nodeId);
    }

    @GetMapping("")
    public List<FileSystemNode> getNodes(@RequestParam ObjectId parentId) {
        return fileSystemNodeRepository.findAllByParentId(parentId);
    }

}

