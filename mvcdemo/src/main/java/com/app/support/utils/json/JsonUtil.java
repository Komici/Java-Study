package com.app.support.utils.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

public class JsonUtil {
    private static ObjectMapper om = new ObjectMapper();
    private static JsonFactory jsonFactory = new JsonFactory();

    public JsonUtil() {
    }

    public static String formatToStr(Object obj) {
        String rst = "";
        if (obj != null) {
            try {
                rst = om.writeValueAsString(obj);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }

        return rst;
    }

    public static Map<String, Object> formatToMap(String json) {
        return (Map)formatObject(json, Map.class);
    }

    public static <T> T formatObject(String json, Class<T> c) {
        T t = null;
        if (StringUtils.isNotBlank(json) && c != null) {
            try {
                t = om.readValue(json, c);
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

        return t;
    }

    public static <T> List<T> formatToList(String json, Class<T> c) {
        return (List)toCollection(json, c, new ArrayList());
    }

    public static <T> Set<T> formatToSet(String json, Class<T> c) {
        return (Set)toCollection(json, c, new HashSet());
    }

    private static <T> Collection<T> toCollection(String json, Class<T> c, Collection<T> coll) {
        JsonParser jp = null;

        try {
            jp = jsonFactory.createParser(json);
            jp.nextToken();

            while(jp.nextToken() == JsonToken.START_OBJECT) {
                T t = om.readValue(jp, c);
                coll.add(t);
            }
        } catch (Exception var12) {
            throw new RuntimeException(var12);
        } finally {
            if (jp != null) {
                try {
                    jp.close();
                } catch (IOException var11) {
                    throw new RuntimeException(var11);
                }
            }

        }

        return coll;
    }
}
