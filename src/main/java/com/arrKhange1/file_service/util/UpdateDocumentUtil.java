package com.arrKhange1.file_service.util;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.Update;

public class UpdateDocumentUtil {
    public static Update excludeNullFieldsFromDocuments(Document object) {
        Update update = new Update();
        for (String key : object.keySet()) {
            Object value = object.get(key);
            if(value != null){
                update.set(key, value);
            }
        }
        return update;
    }
}
