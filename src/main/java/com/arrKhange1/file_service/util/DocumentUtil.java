package com.arrKhange1.file_service.util;

import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;

public class DocumentUtil {
    public static <T> Document getDocumentFromObject(MongoConverter converter, T object) {
        Document document = new Document();
        converter.write(object, document);
        return document;
    }
}
