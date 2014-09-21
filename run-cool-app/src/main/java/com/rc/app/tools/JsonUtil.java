package com.rc.app.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

/**
 * Json帮助类
 * Created by michael 2014/9/18.
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

    public static boolean jsonKeyExist(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key);
    }

}
