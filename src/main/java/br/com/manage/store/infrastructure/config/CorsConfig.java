package br.com.manage.store.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite CORS para todos os endpoints
                .allowedOrigins("*") // Apenas domínios confiáveis
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite apenas métodos necessários
                .allowedHeaders("Authorization", "Content-Type") // Apenas cabeçalhos necessários
                .allowCredentials(false); // Permite cookies ou credenciais
    }
}
