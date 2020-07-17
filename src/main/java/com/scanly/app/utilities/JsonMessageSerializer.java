package com.scanly.app.utilities;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class JsonMessageSerializer<T> extends JsonSerializer<JsonMessage<T>> {

    @Override
    public void serialize(JsonMessage<T> data, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("result", data.getResult());
        jsonGenerator.writeEndObject();
    }

}

