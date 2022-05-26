package com.app.support.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ApiExcludeRegisterCenter {
    private static Set<String> excludeList = new HashSet();

    public ApiExcludeRegisterCenter() {
    }

    public static void register(String uri) {
        excludeList.add(uri);
    }

    public static void register(String[] array) {
        if (array != null && array.length > 0) {
            excludeList.addAll(Arrays.asList(array));
        }

    }

    public static boolean isExcluded(String url) {
        if (!excludeList.isEmpty()) {
            Iterator var1 = excludeList.iterator();

            while(var1.hasNext()) {
                String u = (String)var1.next();
                if (url.indexOf(u) > -1) {
                    return true;
                }
            }
        }

        return false;
    }
}