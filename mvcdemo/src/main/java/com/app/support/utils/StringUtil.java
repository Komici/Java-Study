package com.app.support.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public StringUtil() {
    }

    public static String substr(String source, int beginIndex, int endIndex) {
        return StringUtils.isNotBlank(source) ? new String(source.substring(beginIndex, endIndex)) : null;
    }

    public static Integer getMinNumber(String[] nums) {
        Integer minNum = -1;
        if (nums != null && nums.length > 0) {
            for(int i = 0; i < nums.length; ++i) {
                Integer n = Integer.parseInt(nums[i]);
                if (minNum == -1) {
                    minNum = n;
                } else if (n < minNum) {
                    minNum = n;
                }
            }

            return minNum;
        } else {
            return minNum;
        }
    }

    public static String objToStr(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    public static Long objToLong(Object obj) {
        if (obj != null) {
            try {
                return Long.parseLong(obj.toString());
            } catch (NumberFormatException var2) {
            }
        }

        return null;
    }

    public static Integer objToInt(Object obj) {
        if (obj != null) {
            try {
                return Integer.parseInt(obj.toString());
            } catch (NumberFormatException var2) {
            }
        }

        return null;
    }

    public static Float objToFloat(Object obj) {
        if (obj != null) {
            try {
                return Float.valueOf(obj.toString());
            } catch (NumberFormatException var2) {
            }
        }

        return null;
    }

    public static Boolean objToBool(Object obj) {
        if (obj != null) {
            String o = obj.toString();
            if ("1".equals(o)) {
                return true;
            }

            if ("0".equals(o)) {
                return false;
            }

            try {
                return Boolean.parseBoolean(o);
            } catch (NumberFormatException var3) {
            }
        }

        return false;
    }

    public static String toAscii(String s) {
        StringBuffer result = new StringBuffer();
        char[] chars = s.toCharArray();

        for(int i = 0; i < chars.length; ++i) {
            result.append("[").append(chars[i]).append("]");
        }

        return result.toString();
    }

    public static String toAscii(String str, String s) {
        char[] chars = s.toCharArray();

        for(int i = 0; i < chars.length; ++i) {
            str = str.replace(chars[i] + "", "[" + chars[i] + "]");
        }

        return str;
    }

    public static String asciiToString(String ascii) {
        String regex = "\\[(.*?)\\]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ascii);

        while(m.find()) {
            try {
                ascii = ascii.replace(m.group(0), (char)Integer.parseInt(m.group(1)) + "");
            } catch (Exception var5) {
            }
        }

        return ascii;
    }

    public static boolean isMobileTel(String str) {
        String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return Pattern.matches(REGEX_MOBILE, str);
    }

    public static String encode(String s, String enc) {
        String str;
        try {
            str = URLEncoder.encode(s, enc);
        } catch (UnsupportedEncodingException var4) {
            str = s;
        }

        return str;
    }

    public static String decode(String s, String enc) {
        String str;
        try {
            str = URLDecoder.decode(s, enc);
        } catch (UnsupportedEncodingException var4) {
            str = s;
        }

        return str;
    }

    public static String decodeUTF8(String s) {
        return decode(s, "UTF-8");
    }

    public static String base64Encode(String str) {
        try {
            return Base64.encodeBase64String(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String base64Decode(String encString) {
        try {
            return new String(Base64.decodeBase64(encString), "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String formatIdCard(String idCard) {
        if (idCard == null) {
            return null;
        } else {
            String newIdCard = idCard.trim();
            if (newIdCard.endsWith("x")) {
                char[] chars = newIdCard.toCharArray();
                chars[chars.length - 1] = (char)(chars[chars.length - 1] - 32);
                newIdCard = String.valueOf(chars);
            }

            return newIdCard;
        }
    }
}