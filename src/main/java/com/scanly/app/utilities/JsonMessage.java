package com.scanly.app.utilities;

import lombok.Data;

@Data
public class JsonMessage<T> {
    private T result;

    public JsonMessage(T data) {
        this.result = data;
    }
}
