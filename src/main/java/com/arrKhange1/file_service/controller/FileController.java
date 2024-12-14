package com.arrKhange1.file_service.controller;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.repository.DirectoryDocRepository;
import com.arrKhange1.file_service.repository.FileDocRepository;
import com.arrKhange1.file_service.repository.FileSystemNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/fs-nodes")
public class FileController {

    @Autowired
    private FileSystemNodeRepository fileSystemNodeRepository;

    @Autowired
    private DirectoryDocRepository directoryDocRepository;

    @Autowired
    private FileDocRepository fileDocRepository;

    @PostMapping("file")
    public void addFile(@RequestBody() FileDoc fileDoc) {
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

    @GetMapping("")
    public List<FileSystemNode> getNodes() {
        return fileSystemNodeRepository.findAll();
    }

}

