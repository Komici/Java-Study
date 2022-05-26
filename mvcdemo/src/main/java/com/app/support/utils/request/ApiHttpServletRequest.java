package com.app.support.utils.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ApiHttpServletRequest extends HttpServletRequestWrapper {
    private static final String MEMBER_ID = "apiLoginUserId";
    private Long apiLoginUserId = null;

    public ApiHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    public ApiHttpServletRequest(HttpServletRequest request, Long apiLoginUserId) {
        super(request);
        this.apiLoginUserId = apiLoginUserId;
    }

    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value == null && "apiLoginUserId".equals(name) && this.apiLoginUserId != null) {
            value = this.apiLoginUserId.toString();
        }

        return value;
    }

    public Map getParameterMap() {
        Map map = super.getParameterMap();
        if (!((Map)map).containsKey("apiLoginUserId") && this.apiLoginUserId != null) {
            map = new HashMap((Map)map);
            ((Map)map).put("apiLoginUserId", this.apiLoginUserId.toString());
        }

        return (Map)map;
    }

    public Enumeration getParameterNames() {
        return super.getParameter("apiLoginUserId") == null && this.apiLoginUserId != null ? Collections.enumeration(this.getParameterMap().keySet()) : super.getParameterNames();
    }

    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null && "apiLoginUserId".equals(name) && this.apiLoginUserId != null) {
            values = new String[]{this.apiLoginUserId.toString()};
        }

        return values;
    }
}
