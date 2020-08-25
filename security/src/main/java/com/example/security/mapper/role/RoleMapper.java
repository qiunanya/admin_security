package com.example.security.mapper.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.security.entitys.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Lan
 * @since 2019-04-04
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
     /**
       * <p>
       * 角色表 服务实现类
       * </p>
       * @param userId 用户id
       * @author qiu
       * @since 2020-03-87
       * @return  List<Role>
       */
    List<Role> getRoleListByUserId(String userId);
}
