package com.arrKhange1.file_service.mapper;

import com.arrKhange1.file_service.dto.DirectoryMutationRequestDTO;
import com.arrKhange1.file_service.dto.FileMutationRequestDTO;
import com.arrKhange1.file_service.entity.DirectoryDoc;
import com.arrKhange1.file_service.entity.FileDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileSystemNodeMapper {
    FileDoc fromFileMutationRequestDTO(FileMutationRequestDTO addFileRequestDTO);
    DirectoryDoc fromDirectoryMutationRequestDTO(DirectoryMutationRequestDTO directoryMutationRequestDTO);
}
