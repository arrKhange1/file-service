package com.arrKhange1.file_service.controller;

import com.arrKhange1.file_service.dto.AddDirectoryRequestDTO;
import com.arrKhange1.file_service.dto.AddFileRequestDTO;
import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.mapper.FileSystemNodeMapper;
import com.arrKhange1.file_service.service.FileSystemNodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fs-nodes")
@RequiredArgsConstructor
public class FileSystemNodeController {

    private final FileSystemNodeService fileSystemNodeService;
    private final FileSystemNodeMapper fileSystemNodeMapper;

    /*
     type - необязательно. если передается, надо проверить его. а так type сетится автоматически
     parentId - необязательно
     description - необязательно
     + надо проверить, что входящий объект удовлетворяет схеме документа
    * */
    @PostMapping("file")
    public void addFile(@Valid @RequestBody AddFileRequestDTO fileRequestDTO) {
        FileDoc fileDoc = fileSystemNodeMapper.fromAddFileRequestDTO(fileRequestDTO);
        fileSystemNodeService.addFile(fileDoc);
    }

    /*
     parentId не может быть с type = FILE
    * */
    @PostMapping("directory")
    public void addDirectory(@RequestBody AddDirectoryRequestDTO directoryRequestDTO) {
        DirectoryDoc directoryDoc = fileSystemNodeMapper.fromAddDirectoryRequestDTO(directoryRequestDTO);
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

