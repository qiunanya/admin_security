#项目说明
## 1、基础目录
### config 配置类
### dto  参数类
### entitys 实体类
### enums 枚举
### mapper DAO接口层
### service 服务接口和实现类
### utils 工具类

## 2、spring security 

### 2.1 security 注意事项
####configure(HttpSecurity security) 配置时范围大的在后面，范围小的必须放前面，因为范围大的放前面会覆盖放在后面范围小的导致范围小的配置不生效；

####启用security注解授权方式，需要在主配置类名上加上启用注解：
 @Configuration
 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled =true,securedEnabled=true,jsr250Enabled=true)
   
### 2.2 常用权限注解
#### 1、@RolesAllowed({"USER", "ADMIN"})
说明：具有USER, ADMIN任意一种权限就可以访问。这里可以省略前缀ROLE_，实际的权限可能是ROLE_ADMIN

#### 2、@PreAuthorize 
说明：适合进入方法之前验证授权

#### 3、@PostAuthorize  
说明：检查授权方法之后才被执行
@PreAuthorize("hasAnyAuthority('role:list','role:add')") ：任何一个权限可以访问
@PreAuthorize("hasRole('ADMIN')")：只有ADMIN角色可以访问

#### 4、@PostFilter  
说明：在方法执行之后执行，而且这里可以调用方法的返回值，然后对返回值进行过滤或处理或修改并返回

#### 5、@PreFilter  
说明：在方法执行之前执行，而且这里可以调用方法的参数，然后对参数值进行过滤或处理或修改

### 踩坑提醒：
1、使用@PreAuthorize("hasAuthority('ROLE_BOSS')")时，必须加上角色ROLE_,否则security报无权限错误

