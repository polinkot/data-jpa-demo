package com.example.datajpademo.exception;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ObjectNotFoundException extends Exception {
    private Set<Object> ids = new HashSet<>();

    public ObjectNotFoundException(Object objectId) {
        this.ids.add(objectId);
    }

    @Override
    public String getMessage() {
        return "Объекты не найдены: ids = " + ids;
    }
}