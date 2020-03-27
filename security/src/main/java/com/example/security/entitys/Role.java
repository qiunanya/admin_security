package com.example.security.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
 /**
   * <p>
   * 角色表 服务实现类
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableId
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态，0正常，-1删除
     */
    private String roleStatus;


}
