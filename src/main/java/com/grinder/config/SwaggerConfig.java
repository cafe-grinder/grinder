package com.grinder.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Cafe Grinder API") // API 제목
                .description("카페 정보와 메뉴에 대한 정보가 있는 커뮤니티 중점으로 하는 Cafe Grinder 프로젝트의 API 명세서입니다.") // API 설명
                .version("1.0.0"); // API 버전
    }
}
