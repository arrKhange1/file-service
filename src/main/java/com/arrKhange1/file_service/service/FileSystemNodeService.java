package com.arrKhange1.file_service.service;

import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import com.arrKhange1.file_service.entity.FileSystemNode;
import com.arrKhange1.file_service.repository.DirectoryDocRepository;
import com.arrKhange1.file_service.repository.FileDocRepository;
import com.arrKhange1.file_service.repository.FileSystemNodeRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FileSystemNodeService {
    @Autowired
    private FileSystemNodeRepository fileSystemNodeRepository;

    @Autowired
    private DirectoryDocRepository directoryDocRepository;

    @Autowired
    private FileDocRepository fileDocRepository;

    public void addFile(FileDoc fileDoc) {
        fileDocRepository.save(fileDoc);
    }

    public void addDirectory(DirectoryDoc directoryDoc) {
        directoryDocRepository.save(directoryDoc);
    }

    public void updateFileFieldsFrom(FileDoc fileFields, ObjectId fileId) {
        fileDocRepository.updateFileFieldsFrom(fileFields, fileId);
    }

    public void updateDirectoryFieldsFrom(DirectoryDoc directoryFields, ObjectId directoryId) {
        directoryDocRepository.updateDirectoryFieldsFrom(directoryFields, directoryId);
    }

    public void deleteNodeByIdRecursive(ObjectId nodeId) {
        fileSystemNodeRepository.deleteNodeByIdRecursive(nodeId);
    }

    public List<FileSystemNode> findAllByParentId(ObjectId parentId) {
        return fileSystemNodeRepository.findAllByParentId(parentId);
    }
}
