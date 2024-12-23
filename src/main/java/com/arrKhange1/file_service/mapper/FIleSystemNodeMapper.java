package com.arrKhange1.file_service.mapper;

import com.arrKhange1.file_service.dto.AddFileRequestDTO;
import com.arrKhange1.file_service.entity.FileDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FIleSystemNodeMapper {
    FileDoc fromAddFileRequestDTO(AddFileRequestDTO addFileRequestDTO);
}
