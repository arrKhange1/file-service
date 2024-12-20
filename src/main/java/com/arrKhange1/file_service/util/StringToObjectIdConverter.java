package com.arrKhange1.file_service.util;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

public class StringToObjectIdConverter implements Converter<String, ObjectId> {
    @Override
    public ObjectId convert(String from) {
        if (from.equals("null")) return null;
        return new ObjectId(from);
    }
}
