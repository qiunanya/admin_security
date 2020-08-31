package com.example.security.config;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

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
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUserDetails extends User {

    private static final long serialVersionUID = 1L;

    private String userId;

    public LoginUserDetails(String userId,String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
         this.userId = userId;
    }
}
