package br.com.manage.store.infrastructure.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Management Store Showroom API")
                        .version("1.0")
                        .description("Documentação da API de Gestão de Clientes: Realize operações de CRUD para gerenciar os clientes da loja.")
                        .contact(new Contact()
                                .name("DEVELOPER")
                                .url("https://github.com/SampaioForce-Tech-Labs")
                                .email("vinicius@dhouses.com.br"))
                        .license(new License()
                                .name("API licença URL"))
                );
    }
}
