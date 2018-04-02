package com.example.ro_fa.gesproyectos.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GsonDateAdapterFactory {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss-hh:mm");

    private static final Gson gson = new
            GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    try {
                        return simpleDateFormat.parse(json.getAsString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }
    ).create();

    public static Gson gson() {
        return gson;
    }
}
