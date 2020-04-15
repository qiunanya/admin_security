package com.example.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * <p>
  *  描述
  * </p>
  * @author NanYa
  * @since 2020-04-03
  */
@Slf4j
public class ResponseUtil implements Serializable {

    public static void write(HttpServletResponse response, Object o) {
        try {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            //json返回
            out.println(JSON.toJSONString(o, SerializerFeature.WriteMapNullValue));
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("e={}", e);
        }
    }

    /**
     * 使用response输出JSON
     *
     * @param response
     * @param resultMap
     */
    public static void out(HttpServletResponse response, Map<String, Object> resultMap) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(toJson(resultMap));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg) {
        return resultMap(flag, code, msg, null);
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg, Object token) {
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("success", flag);
        resultMap.put("msg", msg);
        resultMap.put("code", code);
        resultMap.put("timestamp", System.currentTimeMillis());
        if (token != null) {
            resultMap.put("Token", token);
        }
        return resultMap;
    }

    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg, Object token, List<GrantedAuthority> authorities) {
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("success", flag);
        resultMap.put("msg", msg);
        resultMap.put("code", code);
        resultMap.put("permission",authorities);
        resultMap.put("timestamp", System.currentTimeMillis());
        if (token != null) {
            resultMap.put("Token", token);
        }
        return resultMap;
    }

    public static String toJson(Object object){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return  gson.toJson(object);
    }





}
