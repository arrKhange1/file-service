package com.arrKhange1.file_service.exception;

import org.bson.types.ObjectId;

public class NodeNotFoundException extends RuntimeException {
    public NodeNotFoundException(ObjectId nodeNotFoundId) {
        super(String.format("Couldn't find node with ID: %s", nodeNotFoundId));
    }
}
