package kr.go.data.util.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.ArrayList;
import kr.go.data.util.annotation.NoAuth;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .description("토큰값을 입력하여 인증을 활성화할 수 있습니다.")
                .bearerFormat("JWT")
        );
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    private Info apiInfo() {
        return new Info()
                .title("회원 관리 API")
                .description("변경 가능성 있음")
                .version("0.0.1");
    }


    @Bean
    public OperationCustomizer customOperationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            // HandlerMethod에서 @NoAuth 어노테이션을 검사
            NoAuth noAuthAnnotation = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), NoAuth.class);

            if (noAuthAnnotation != null) {
                // @NoAuth 어노테이션이 있는 경우, 보안 요구 사항을 제거
                operation.setSecurity(new ArrayList<>()); // 보안 요구 사항을 비워 인증 없이 접근 가능하도록 설정
            }
            return operation;
        };
    }

    @Bean // custom-error-controller 제거
    public OpenApiCustomizer removeCustomErrorController() {
        return openApi -> {
            openApi.getPaths().remove("/error");
        };
    }

}

