package com.app.support.utils.request;

import com.app.support.utils.json.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {
    public static final String CONTENT_TYPE_TEXT = "text/html; charset=UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    public ResponseUtils() {
    }

    public static void write(HttpServletResponse response, String contentType, String s) {
        response.reset();
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            out.write(s);
        } catch (IOException var8) {
            var8.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }

        }

    }

    public static void writeText(HttpServletResponse response, String s) {
        write(response, "text/html; charset=UTF-8", s);
    }

    public static void writeJson(HttpServletResponse response, String s) {
        write(response, "application/json;charset=UTF-8", s);
    }

    public static void writeJson(HttpServletResponse response, Object o) {
        String s = JsonUtil.formatToStr(o);
        write(response, "application/json;charset=UTF-8", s);
    }
}