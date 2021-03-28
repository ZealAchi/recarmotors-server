package com.recarmotors.util;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LongDefault0Adapter implements JsonSerializer<Long>, JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equals("null")) {// Definido como un tipo largo, si el fondo devuelve "" o nulo, devuelve 0 
                return 0l;
            }
        } catch (Exception ignore) {
        }
        try {
            return json.getAsLong();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Long src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}