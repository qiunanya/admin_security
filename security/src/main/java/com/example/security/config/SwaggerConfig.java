package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
/**
  * <p>
  *  项目接口文档配置
  * </p>
  *
  * @author qiu
  * @since 2020-08-23
  */
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("Authorization").description("认证token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.security.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Collections.singletonList(builder.build()));
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0.0-SNAPSHOT")
                .description("聊天系统后台接口文档")
                .contact("邱南亚")
                .build();
    }

}
