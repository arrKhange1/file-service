package com.arrKhange1.file_service.service;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.exception.GeneralFileSystemNodeException;
import com.arrKhange1.file_service.exception.NodeNotFoundException;
import com.arrKhange1.file_service.repository.DirectoryDocRepository;
import com.arrKhange1.file_service.repository.FileDocRepository;
import com.arrKhange1.file_service.repository.FileSystemNodeRepository;
import com.arrKhange1.file_service.type.FileSystemNodeType;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FileSystemNodeService {
    private final FileSystemNodeRepository fileSystemNodeRepository;
    private final DirectoryDocRepository directoryDocRepository;
    private final FileDocRepository fileDocRepository;
    private final FileSystemNodeValidationService fileSystemNodeValidationService;

    public FileDoc addFile(FileDoc fileDoc) {
        fileSystemNodeValidationService.validateNodeParent(fileDoc.getParentId());
        fileDoc.setType(FileSystemNodeType.FILE.name());
        return fileDocRepository.save(fileDoc);
    }

    public DirectoryDoc addDirectory(DirectoryDoc directoryDoc) {
        fileSystemNodeValidationService.validateNodeParent(directoryDoc.getParentId());
        directoryDoc.setType(FileSystemNodeType.DIRECTORY.name());
        return directoryDocRepository.save(directoryDoc);
    }

    public void updateFileFieldsFrom(FileDoc fileFields, ObjectId fileId) {
        fileSystemNodeValidationService.nodeExistById(fileId);
        fileSystemNodeValidationService.validateNodeParent(fileFields.getParentId());
        fileSystemNodeValidationService.nodeEqualToParent(fileId, fileFields.getParentId());
        fileDocRepository.updateFileFieldsFrom(fileFields, fileId);
    }

    public void updateDirectoryFieldsFrom(DirectoryDoc directoryFields, ObjectId directoryId) {
        fileSystemNodeValidationService.nodeExistById(directoryId);
        fileSystemNodeValidationService.validateNodeParent(directoryFields.getParentId());
        fileSystemNodeValidationService.nodeEqualToParent(directoryId, directoryFields.getParentId());
        directoryDocRepository.updateDirectoryFieldsFrom(directoryFields, directoryId);
    }

    public void deleteNodeByIdRecursive(ObjectId nodeId) {
        fileSystemNodeValidationService.nodeExistById(nodeId);
        fileSystemNodeRepository.deleteNodeByIdRecursive(nodeId);
    }

    public List<FileSystemNode> findAllByParentId(ObjectId parentId) {
        return fileSystemNodeRepository.findAllByParentId(parentId);
    }
}
