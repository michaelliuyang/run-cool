package com.rc.test.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

/**
 * Created by michael 2014/9/20.
 */
public class JsonUtil {

    private static Gson gson = new GsonBuilder().create();
    private static Gson gsonFormat = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static JsonParser jsonParser = new JsonParser();

    private JsonUtil() {
    }

    public static JSONObject jsonStr2JsonObject(String jsonStr) {
        return new JSONObject(jsonStr);
    }

    public static String bean2JsonStr(Object bean) {
        return gson.toJson(bean);
    }

    public static JsonElement bean2JsonTree(Object bean) {
        return gson.toJsonTree(bean);
    }

    public static String formatJsonStr(String jsonStr) {
        return gsonFormat.toJson(jsonParser.parse(jsonStr));
    }

}
