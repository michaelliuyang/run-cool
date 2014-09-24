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

    /**
     * Json字符串转Json对象
     *
     * @param jsonStr Json字符串
     * @return Json对象
     */
    public static JSONObject jsonStr2JsonObject(String jsonStr) {
        return new JSONObject(jsonStr);
    }

    /**
     * 对象转Json Tree对象
     *
     * @param bean 对象
     * @return Json Tree对象
     */
    public static JsonElement bean2JsonTree(Object bean) {
        return gson.toJsonTree(bean);
    }

    /**
     * 格式化Json字符串
     *
     * @param jsonStr 原始Json字符串
     * @return 格式化后的Json字符串
     */
    public static String formatJsonStr(String jsonStr) {
        return gsonFormat.toJson(jsonParser.parse(jsonStr));
    }

    /**
     * 判断Json对象是否存在某个Key
     *
     * @param jsonObject 待判断的Json对象
     * @param key        待判断的Key
     * @return 是/否
     */
    public static boolean jsonKeyExist(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key);
    }

}
