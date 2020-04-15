package com.example.security.entitys;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
  * <p>
  *   权限缓存redis对象
  * </p>
  *
  * @author qiu
  * @since 2020-04-15
  */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuthorityEntity implements Serializable {
    /**
     *  用户名
     */
    private String username;

    /**
     * 权限集合
     */
    List<String> list;

}
