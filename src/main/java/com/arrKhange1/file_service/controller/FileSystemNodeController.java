package com.arrKhange1.file_service.controller;

import com.arrKhange1.file_service.dto.DirectoryMutationRequestDTO;
import com.arrKhange1.file_service.dto.FileMutationRequestDTO;
import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.mapper.FileSystemNodeMapper;
import com.arrKhange1.file_service.service.FileSystemNodeService;
import jakarta.validation.constraints.NotNull;
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

    @PostMapping("file")
    public FileDoc addFile(
            @Validated({FileMutationRequestDTO.AddValidation.class})
            @RequestBody FileMutationRequestDTO fileRequestDTO) {
        FileDoc fileDoc = fileSystemNodeMapper.fromFileMutationRequestDTO(fileRequestDTO);
        return fileSystemNodeService.addFile(fileDoc);
    }

    @PostMapping("directory")
    public DirectoryDoc addDirectory(
            @Validated({DirectoryMutationRequestDTO.AddValidation.class})
            @RequestBody DirectoryMutationRequestDTO directoryRequestDTO) {
        DirectoryDoc directoryDoc = fileSystemNodeMapper.fromDirectoryMutationRequestDTO(directoryRequestDTO);
        return fileSystemNodeService.addDirectory(directoryDoc);
    }

    @PatchMapping("file/{fileId}")
    public void patchFile(
            @RequestBody FileMutationRequestDTO fileRequestDTO,
            @NotNull(message = "Patching file can't be null") @PathVariable("fileId") ObjectId fileId) {
        FileDoc fileDoc = fileSystemNodeMapper.fromFileMutationRequestDTO(fileRequestDTO);
        fileSystemNodeService.updateFileFieldsFrom(fileDoc, fileId);
    }

    @PatchMapping("directory/{directoryId}")
    public void patchDirectory(
            @RequestBody DirectoryMutationRequestDTO directoryRequestDTO,
            @NotNull(message = "Patching directory can't be null") @PathVariable("directoryId") ObjectId directoryId) {
        DirectoryDoc directoryDoc = fileSystemNodeMapper.fromDirectoryMutationRequestDTO(directoryRequestDTO);
        fileSystemNodeService.updateDirectoryFieldsFrom(directoryDoc, directoryId);
    }

    @DeleteMapping("{nodeId}")
    public void deleteNode(
            @NotNull(message = "Patching directory can't be null")
            @PathVariable("nodeId") ObjectId nodeId) {
        fileSystemNodeService.deleteNodeByIdRecursive(nodeId);
    }

    @GetMapping("")
    public List<FileSystemNode> getNodes(@RequestParam(required = false) ObjectId parentId) {
        return fileSystemNodeService.findAllByParentId(parentId);
    }
}

