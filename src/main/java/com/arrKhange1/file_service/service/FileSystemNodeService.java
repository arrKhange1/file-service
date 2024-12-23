package com.arrKhange1.file_service.service;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.repository.DirectoryDocRepository;
import com.arrKhange1.file_service.repository.FileDocRepository;
import com.arrKhange1.file_service.repository.FileSystemNodeRepository;
import com.arrKhange1.file_service.type.FileSystemNodeType;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    // move validation logic to dedicated FileSystemNodeValidationService
    private boolean isFileSystemNodeDirectory(FileSystemNode fileSystemNode) {
        return Objects.equals(fileSystemNode.getType(), FileSystemNodeType.DIRECTORY.name());
    }

    private void nodeExistById(ObjectId id) {
        if (!fileSystemNodeRepository.existsById(id)) throw new RuntimeException(String.format("Node with ID %s doesn't exist", id));
    }

    private void validateNodeParent(ObjectId parentId) {
        if (parentId != null) {
            Optional<FileSystemNode> fileSystemNode = fileSystemNodeRepository.findById(parentId);
            if (fileSystemNode.isEmpty()) throw new RuntimeException(String.format("Couldn't find node with ID: %s", parentId));
            if (!isFileSystemNodeDirectory(fileSystemNode.get())) throw new RuntimeException("Only directory can be a parental node");
            if (fileSystemNode.get().get_id().equals(parentId)) throw new RuntimeException("Node can't be a parent of itself");
        }
    }

    public void addFile(FileDoc fileDoc) {
        validateNodeParent(fileDoc.getParentId());
        fileDoc.setType(FileSystemNodeType.FILE.name());
        fileDocRepository.save(fileDoc);
    }

    public void addDirectory(DirectoryDoc directoryDoc) {
        validateNodeParent(directoryDoc.getParentId());
        directoryDoc.setType(FileSystemNodeType.DIRECTORY.name());
        directoryDocRepository.save(directoryDoc);
    }

    public void updateFileFieldsFrom(FileDoc fileFields, ObjectId fileId) {
        nodeExistById(fileId);
        validateNodeParent(fileFields.getParentId());
        fileDocRepository.updateFileFieldsFrom(fileFields, fileId);
    }

    public void updateDirectoryFieldsFrom(DirectoryDoc directoryFields, ObjectId directoryId) {
        nodeExistById(directoryId);
        validateNodeParent(directoryFields.getParentId());
        directoryDocRepository.updateDirectoryFieldsFrom(directoryFields, directoryId);
    }

    public void deleteNodeByIdRecursive(ObjectId nodeId) {
        nodeExistById(nodeId);
        fileSystemNodeRepository.deleteNodeByIdRecursive(nodeId);
    }

    public List<FileSystemNode> findAllByParentId(ObjectId parentId) {
        return fileSystemNodeRepository.findAllByParentId(parentId);
    }
}
