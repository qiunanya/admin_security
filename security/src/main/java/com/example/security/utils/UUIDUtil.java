package com.example.security.utils;

import java.util.UUID;

/**
 * @author: Lan
 * @date: 2019/4/8 10:01
 * @description:
 */
public class UUIDUtil {

    /**
     * 生成唯一id
     *
     * @return
     */
    public static String createId() {
        String string = UUID.randomUUID().toString().replace("-", "");
        return string.substring(0,10);
    }

}
