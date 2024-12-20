package com.arrKhange1.file_service.util;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Update;

public class UpdateDocumentUtil {
    private static final Logger log = LoggerFactory.getLogger(UpdateDocumentUtil.class);

    private static Update excludeNullFieldsFromDocuments(Document object) {
        Update update = new Update();
        for (String key : object.keySet()) {
            log.info("key " + key + " value " + object.get(key));
            Object value = object.get(key);
            if(value != null){
                update.set(key, value);
            }
        }
        return update;
    }

    public static <T> Update preparePatchObjectForUpdateOperation(MongoConverter converter, T fromObject) {
        Document document = DocumentUtil.getDocumentFromObject(converter, fromObject);
        log.info(document.toString());
        var t = excludeNullFieldsFromDocuments(document);
        log.info(t.toString());
        return excludeNullFieldsFromDocuments(document);
    }
}
