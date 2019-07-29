package com.oocl.web.parkingLot.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Created with IDEA
 *
 * @author:linGuangXiong
 * @Date:2019/7/29
 * @Time:10:02
 * @description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // Restful API均生成JSON字符串
                .produces(Collections.singleton("application/json;charset=UTF-8"))
                .apiInfo(apiInfo())
                .select()

                // 扫描有ApiOperation注解的接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }



    //
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("FocusTeam-停车管理系统")
                .description("停车管理系统接口一览")
                .version("2.0")
                .build();
    }

}

