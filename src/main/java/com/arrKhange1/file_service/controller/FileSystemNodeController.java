package com.arrKhange1.file_service.controller;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.service.FileSystemNodeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fs-nodes")
public class FileSystemNodeController {

    @Autowired
    private FileSystemNodeService fileSystemNodeService;

    @PostMapping("file")
    public void addFile(@RequestBody FileDoc fileDoc) {
        fileSystemNodeService.addFile(fileDoc);
    }

    @PostMapping("directory")
    public void addDirectory(@RequestBody DirectoryDoc directoryDoc) {
        fileSystemNodeService.addDirectory(directoryDoc);
    }

    @PatchMapping("file/{fileId}")
    public void patchFile(@RequestBody FileDoc fileDoc, @PathVariable("fileId") ObjectId fileId) {
        fileSystemNodeService.updateFileFieldsFrom(fileDoc, fileId);
    }

    @PatchMapping("directory/{directoryId}")
    public void patchFile(@RequestBody DirectoryDoc directoryDoc, @PathVariable("directoryId") ObjectId directoryId) {
        fileSystemNodeService.updateDirectoryFieldsFrom(directoryDoc, directoryId);
    }

    // TODO: проверять есть ли такой id перед удалением
    @DeleteMapping("{nodeId}")
    public void deleteNode(@PathVariable("nodeId") ObjectId nodeId) {
        fileSystemNodeService.deleteNodeByIdRecursive(nodeId);
    }

    @GetMapping("")
    public List<FileSystemNode> getNodes(@RequestParam(required = false) ObjectId parentId) {
        return fileSystemNodeService.findAllByParentId(parentId);
    }
}

