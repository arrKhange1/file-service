package com.arrKhange1.file_service.service;

import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.exception.GeneralFileSystemNodeException;
import com.arrKhange1.file_service.exception.NodeNotFoundException;
import com.arrKhange1.file_service.repository.FileSystemNodeRepository;
import com.arrKhange1.file_service.type.FileSystemNodeType;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FileSystemNodeValidationService {

    private final FileSystemNodeRepository fileSystemNodeRepository;

    public boolean isFileSystemNodeDirectory(FileSystemNode fileSystemNode) {
        return Objects.equals(fileSystemNode.getType(), FileSystemNodeType.DIRECTORY.name());
    }

    public void nodeExistById(ObjectId id) {
        if (!fileSystemNodeRepository.existsById(id)) throw new NodeNotFoundException(id);
    }

    public void validateNodeParent(ObjectId parentId) {
        if (parentId != null) {
            Optional<FileSystemNode> fileSystemNode = fileSystemNodeRepository.findById(parentId);
            if (fileSystemNode.isEmpty()) throw new NodeNotFoundException(parentId);
            if (!isFileSystemNodeDirectory(fileSystemNode.get())) throw new GeneralFileSystemNodeException("Only directory can be a parental node");
//            if (fileSystemNode.get().get_id().equals(parentId)) throw new GeneralFileSystemNodeException("Node can't be a parent of itself");
        }
    }
}
