package com.example.security.utils;


import com.example.security.enums.ResultEnum;
import lombok.Data;
/**
* 描述: <br>
* @Author QiuKing
* @Date 2020/3/19 16:01
*/

@Data
public class ResultUtil<T> {

    private Integer code;

    private String msg;

    private boolean success;

    private String test_msg;

    private T data;

    public ResultUtil() {
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static ResultUtil success(Object data) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSuccess(true);
        resultUtil.setData(data);
        resultUtil.setMsg("请求成功");
        return resultUtil;
    }

    public static ResultUtil loginSuccess(Object data) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(200);
        resultUtil.setSuccess(true);
        resultUtil.setData(data);
        resultUtil.setMsg("登录成功");
        return resultUtil;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultUtil success(String... msg) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        resultUtil.setSuccess(true);
        resultUtil.setMsg(msg.toString());
        return resultUtil;
    }

    /**
     * 错误返回
     *
     * @param resultEnum
     * @return
     */
    public static ResultUtil error(ResultEnum resultEnum) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSuccess(false);
        resultUtil.setCode(resultEnum.getCode());
        resultUtil.setMsg(resultEnum.getMsg());
        return resultUtil;
    }

    /**
     * 返回失败
     *
     * @param msg
     * @return
     */
    public static ResultUtil error(String msg) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setSuccess(false);
        resultUtil.setCode(-1);
        resultUtil.setMsg(msg);
        return resultUtil;
    }
}
