package com.example.security.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BaseEntity
 * @Author QiuKing
 * @Date 2019/12/3116:53
 */
@Data
public class BaseEntity implements Serializable {
    private Integer page;
    private Integer pageSize;
    private boolean asc = false;

}
