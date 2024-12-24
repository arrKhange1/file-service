package com.arrKhange1.file_service.dto;


import java.util.Map;

public record ErrorDTO(String description, Map<String, String> details){
}
