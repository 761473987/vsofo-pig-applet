package com.vsofo.common.component;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description: swagger登录接口
 * @author: liuzhiyun
 * @create: 2020-07-27 15:47
 **/
@Component
public class SwaggerAddition implements ApiListingScannerPlugin {
        /**
         * Implement this method to manually add ApiDescriptions
         * 实现此方法可手动添加ApiDescriptions
         *
         * @param context - Documentation context that can be used infer documentation context
         * @return List of {@link ApiDescription}
         * @see ApiDescription
         */
        @Override
        public List<ApiDescription> apply(DocumentationContext context) {
            Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
                    .method(HttpMethod.POST)
                    .summary("用户名密码登录")
                    .notes("account/password登录")
                    .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) // 接收参数格式
                    .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
                    .tags(Sets.newHashSet("登录"))
                    .parameters(Arrays.asList(
                            new ParameterBuilder()
                                    .description("用户名")
                                    .type(new TypeResolver().resolve(String.class))
                                    .name("account")
                                    .parameterType("query")
                                    .parameterAccess("access")
                                    .required(true)
                                    .modelRef(new ModelRef("string"))
                                    .build(),
                            new ParameterBuilder()
                                    .description("密码")
                                    .type(new TypeResolver().resolve(String.class))
                                    .name("password")
                                    .defaultValue("123456")
                                    .parameterType("query")
                                    .parameterAccess("access")
                                    .required(true)
                                    .modelRef(new ModelRef("string"))
                                    .build()
                    ))
                    .responseMessages(Collections.singleton(
                            new ResponseMessageBuilder().code(200).message("请求成功")
                                    .responseModel(new ModelRef(
                                            "String")
                                    ).build()))
                    .build();
        
            ApiDescription loginApiDescription = new ApiDescription("/auth/pig/auth/dologin", "login",
                    Arrays.asList(usernamePasswordOperation), false);
        
            return Arrays.asList(loginApiDescription);
    }
    
    @Override
    public boolean supports(DocumentationType documentationType) {
            return DocumentationType.SWAGGER_2.equals(documentationType);
        }
}
