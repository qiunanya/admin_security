package com.example.security.utils;


import com.example.security.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
* 描述: <br>
* @Author QiuKing
* @Date 2020/3/19 16:01
*/

@Data
@ApiModel(value = "响应体")
public class ResultUtil<T> {
    @ApiModelProperty(value = "响应代码")
    private Integer code;
    @ApiModelProperty(value = "状态标识")
    private ResultEnum message;
    @ApiModelProperty(value = "是否成功 true成功，false失败")
    private boolean success;
    @ApiModelProperty(value = "响应体")
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
        resultUtil.setMessage(ResultEnum.ACCESS_REQUEST_SUCCESS);
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
        resultUtil.setMessage(ResultEnum.ACCESS_REQUEST_SUCCESS);
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
        resultUtil.setMessage(ResultEnum.ACCESS_REQUEST_FAIL);
        return resultUtil;
    }
}
