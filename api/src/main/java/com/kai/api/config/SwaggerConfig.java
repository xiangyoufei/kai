package com.kai.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger2配置类
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kai API")
                .description("Kai API")
                .version("1.0.0")
                .contact(new Contact("刘阳","", "liuyang.sx@qq.com"))
                .build();
    }

    @Bean
    public Docket customImplementation(){
        List<Parameter> pars = new ArrayList<>();
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("DDC配置工具")  //必须去掉，否则通过网关方式行不通
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);//去掉swagger默认的状态码
//                .globalOperationParameters(pars)
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
    }

//    private List<SecurityScheme> securitySchemes() {
//        return newArrayList(
//                new ApiKey("Authorization", "X-SSO-FullticketId", "header"));
//    }
//    private List<SecurityContext> securityContexts() {
//        return newArrayList(
//                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
//                        .build()
//        );
//    }
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return newArrayList(
//                new SecurityReference("Authorization", authorizationScopes));
//    }

}
