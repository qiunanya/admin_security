package com.example.security.config;

import com.example.security.entitys.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

 /**
   * <p>
   *   考虑到user中只有账号和密码，为了获取到原始的UserEntity对象，专门创建这个类对
   *   spring security 自带的User类进行扩展
   *  扩展spring security的用户对象，返回用户完整信息
   * </p>
   *
   * @author qiu
   * @since 2020-03-87
   */
public class ExtendSecurityUser extends User {
     /**
      * 原始对象
      * UserEntity originalUser ：传入原始的UserEntity对象
      * List<GrantedAuthority> authorities ：创建角色、权限信息集合
      */
    private UserEntity originalUser;
    public ExtendSecurityUser(UserEntity originalUser, List<GrantedAuthority> authorities){
        /**
         * 调用父类构造器
         */
        super(originalUser.getUserName(),originalUser.getUserPassword(),authorities);
        /**
         * 给本类的this.originalUser 赋值
         */
        this.originalUser = originalUser;
     }

     /**
      * 对外提供的获取原始UserEntity 对象 的get方法
      * @return
      */
    public UserEntity getOriginalUser() {
        return originalUser;
    }

}
