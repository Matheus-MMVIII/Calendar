package com.calendar.http.util;

public final class JsonUtil {
    private JsonUtil() {

    }

    public static String object(String key, String value) {
        return "{\"" + escape(key) + "\":\"" + escape(value) + "\"}";
    }

    public static String error(String message) {
        return "{\"error\":\"" + escape(message) + "\"}";
    }

    private static String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
