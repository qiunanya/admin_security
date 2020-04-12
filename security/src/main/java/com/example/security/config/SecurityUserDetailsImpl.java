package com.example.security.config;

import com.example.security.entitys.Role;
import com.example.security.entitys.UserEntity;
import com.example.security.utils.StaticConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserDetailsImpl extends UserEntity implements UserDetails {

    /**
     * 是否记住密码
     */
    private Boolean remember;

    private Collection<? extends GrantedAuthority> authorities;

    private List<Role> roleList;

    private String token;

    private UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public SecurityUserDetailsImpl(UserEntity user, Collection<? extends GrantedAuthority> authorities, List<Role> roles, String token){
        this.authorities = authorities;
        this.setUserName(user.getUserName());
        this.setUserPhone(user.getUserPhone());
        this.setUserCreateTime(user.getUserCreateTime());
        this.setUserPassword(user.getUserPassword());
        this.setUserStatus(user.getUserStatus());
        this.setAuthorities(authorities);
        this.setRoleList(roles);
        this.setToken(token);
        this.user = user;
    }

    @Override
    public String getPassword() {
        return this.getUserPassword();
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    /**
     * 账户是否过期 0正常 -1删除
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return StaticConstant.ZERO.equals(user.getUserStatus());
    }

    /**
     * 是否锁定 0正常 -1删除
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return StaticConstant.USER_ACCOUNT_NORMAL.equals(user.getLockStatus());
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用 0启用 -1禁用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return StaticConstant.USER_FORBIDDEN_ACCOUNT.equals(user.getForbiddenStatus());
    }
}
