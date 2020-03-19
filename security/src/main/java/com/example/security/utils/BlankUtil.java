package com.example.security.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Lan
 * @description 判断是否为空的工具类
 * @date 2019/4/8 15:48
 */

public class BlankUtil {

    /**
     * 私有化构造方法 防止被实例化
     */
    private BlankUtil() {
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isBlank(final String str) {
        return (str == null) || (str.trim().length() <= 0);
    }

    /**
     * 判断字符是否为空
     *
     * @param cha
     * @return
     */
    public static boolean isBlank(final Character cha) {
        return (cha == null) || cha.equals(' ');
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isBlank(final Object obj) {
        return (obj == null);
    }

    /**
     * 判断数组是否为空
     *
     * @param objs
     * @return
     */
    public static boolean isBlank(final Object[] objs) {
        return (objs == null) || (objs.length <= 0);
    }

    /**
     * 判断Collection是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(final Collection<?> obj) {
        return (obj == null);
    }

    /**
     * 判断Set是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(final Set<?> obj) {
        return (obj == null);
    }

    public static boolean isBlank(Integer i) {
        return i == null || i < 1;
    }

    /**
     * 判断Serializable是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(final Serializable obj) {
        return obj == null;
    }

    /**
     * 判断Map是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(final Map<?, ?> obj) {
        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * 判断List是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(final List<?> obj) {
        return (obj == null || (obj.size()) <= 0);
    }
}
