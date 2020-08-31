package com.example.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseEntity
 * @Author QiuKing
 * @Date 2019/12/3116:53
 */
@Data
@ApiModel(value = "基础类")
public class BaseEntity implements Serializable {
    @ApiModelProperty(value = "当前页")
    private Integer page;
    @ApiModelProperty(value = "条数")
    private Integer size;
    @ApiModelProperty(value = "搜索关键词")
    private String search;
    @ApiModelProperty(value = "排序规则 asc升序 desc降序")
    private boolean asc = false;

}
