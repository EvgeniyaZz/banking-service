package com.github.EvgeniyaZz.bankingservice.config;

import com.github.EvgeniyaZz.bankingservice.model.User;
import com.github.EvgeniyaZz.bankingservice.to.UserTo;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi token() {
        return GroupedOpenApi.builder()
                .group("JWT Token")
                .addOpenApiCustomizer(openApi -> {
                    openApi.addSecurityItem(new SecurityRequirement().addList("Authorization"))
                            .components(new Components()
                                    .addSecuritySchemes("Authorization", new io.swagger.v3.oas.models.security.SecurityScheme()
                                            .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("basic"))
                            )
                            .info(new Info().title("JWT Token").description("""
                                    Service for banking operations
                                    <p>Тестовые креденшелы:<br>
                                       - User / password<br>
                                       - Admin / admin<br>
                                       - User2 / user2</p>
                                    </p>
                                    """));

                })
                .pathsToMatch("/token")
                .build();
    }

    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("REST API")
                .addOpenApiCustomizer(openApi -> {
                    openApi.addSecurityItem(new SecurityRequirement().addList("Authorization"))
                            .components(new Components()
                                    .addSchemas("User", ModelConverters.getInstance().readAllAsResolvedSchema(User.class).schema)
                                    .addSchemas("UserTo", ModelConverters.getInstance().readAllAsResolvedSchema(UserTo.class).schema)
                                    .addSecuritySchemes("Authorization", new SecurityScheme()
                                            .in(SecurityScheme.In.HEADER)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .name("JWT"))
                            )
                            .info(new Info().title("REST API").version("1.0").description("""
                                    Service for banking operations
                                    <p>Авторизация через JWT Token (справа верху `Select a definition`)</p>
                                    """));
                })
                .pathsToMatch("/api/**")
                .build();
    }
}
