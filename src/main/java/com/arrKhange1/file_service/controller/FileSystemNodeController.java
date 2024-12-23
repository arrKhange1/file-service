package com.arrKhange1.file_service.controller;

import com.arrKhange1.file_service.dto.DirectoryMutationRequestDTO;
import com.arrKhange1.file_service.dto.FileMutationRequestDTO;
import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.mapper.FileSystemNodeMapper;
import com.arrKhange1.file_service.service.FileSystemNodeService;
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
    public void addFile(
            @Validated({FileMutationRequestDTO.AddValidation.class})
            @RequestBody FileMutationRequestDTO fileRequestDTO) {
        FileDoc fileDoc = fileSystemNodeMapper.fromFileMutationRequestDTO(fileRequestDTO);
        fileSystemNodeService.addFile(fileDoc);
    }

    /*
     parentId не может быть с type = FILE
    * */
    @PostMapping("directory")
    public void addDirectory(@RequestBody DirectoryMutationRequestDTO directoryRequestDTO) {
        DirectoryDoc directoryDoc = fileSystemNodeMapper.fromDirectoryMutationRequestDTO(directoryRequestDTO);
        fileSystemNodeService.addDirectory(directoryDoc);
    }

    @PatchMapping("file/{fileId}")
    public void patchFile(
            @Validated({FileMutationRequestDTO.PatchValidation.class})
            @RequestBody FileMutationRequestDTO fileRequestDTO,
            @PathVariable("fileId") ObjectId fileId) {
        FileDoc fileDoc = fileSystemNodeMapper.fromFileMutationRequestDTO(fileRequestDTO);
        fileSystemNodeService.updateFileFieldsFrom(fileDoc, fileId);
    }

    @PatchMapping("directory/{directoryId}")
    public void patchDirectory(@RequestBody DirectoryMutationRequestDTO directoryRequestDTO,
                               @PathVariable("directoryId") ObjectId directoryId) {
        DirectoryDoc directoryDoc = fileSystemNodeMapper.fromDirectoryMutationRequestDTO(directoryRequestDTO);
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

