package com.fz.demo.excel;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class Utils {
    //    private static final String IP_ADDRESS_AND_PORT = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,
    //    2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])
    //    \\:([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5])";
    private static final String IP_ADDRESS_AND_PORT =
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9])\\:([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5]))";
    private static final String IP_ADDRESS_STRING =
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9]))";
    public static final Pattern IP_ADDRESS = Pattern.compile(IP_ADDRESS_STRING);
    public static final Pattern IP_ADDRESS_PORT = Pattern.compile(IP_ADDRESS_AND_PORT);

    /**
     * 验证是否是ip
     *
     * @param target 要验证的文本
     * @return 是返回true, 否则返回false
     */
    public static boolean isIpAddress(String target) {
        return StringUtils.isNotEmpty(target) && IP_ADDRESS.matcher(target).matches();
    }

    /**
     * 验证是否是ip加端口号
     *
     * @param target 要验证的文本
     * @return 是返回true, 否则返回false
     */
    public static boolean isIpAddressPort(String target) {
        return StringUtils.isNotEmpty(target) && IP_ADDRESS_PORT.matcher(target).matches();
    }

    /**
     * 获取apk编译类型
     *
     * @param buildType 编译类型
     * @param filePath  文件路径
     * @author dingpeihua
     * @date 2019/7/11 18:21
     * @version 1.0
     */
    public static String getBuildType(String buildType, String filePath) {
        if (!StringUtils.isEmpty(buildType) || StringUtils.isEmpty(filePath)) {
            return buildType;
        }
        String lowerCasePath = filePath.toLowerCase();
        //develop  debug deploy release
        if (lowerCasePath.contains("develop")) {
            return "develop";
        } else if (lowerCasePath.contains("debug")) {
            return "debug";
        } else if (lowerCasePath.contains("deploy")) {
            return "deploy";
        } else if (lowerCasePath.contains("release")) {
            return "release";
        }
        return "";
    }

    /**
     * 去掉前后双引号
     *
     * @param text
     * @author dingpeihua
     * @date 2019/7/19 20:01
     * @version 1.0
     */
    public static String removeDoubleQuotes(String text) {
        if (StringUtils.isEmpty(text)) {
            return "";
        }
        if (text.startsWith("\"")) {
            text = text.substring(1);
        }
        if (text.endsWith("\"")) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    public static String toString(Object value) {
        if (value == null) {
            return null;
        }
        String str = String.valueOf(value);
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    /**
     * 将Object对象转成boolean类型
     *
     * @param value
     * @return 如果value不能转成boolean，则默认false
     */
    public static Boolean toBoolean(Object value) {
        return toBoolean(value, false);
    }

    /**
     * 将Object对象转成boolean类型
     *
     * @param value
     * @return 如果value不能转成boolean，则默认defaultValue
     */
    public static Boolean toBoolean(Object value, boolean defaultValue) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            return "true".equalsIgnoreCase((String) value);
        }
        return defaultValue;
    }
}
