package tn.esprit.projet.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenAPIConfig {
    @Configuration
    public class SpringDocConfig {
        @Bean
        public OpenAPI springShopOpenAPI() {
            return new OpenAPI()
                    .info(infoAPI());

        }

        public Info infoAPI() {
            return new Info().title("Documentation")
                    .description("Kaddem")
                    .contact(contactAPI());

        }

        public Contact contactAPI() {
            Contact contact = new Contact().name("Equipe ASI II")
                    .email("karim.trabelsi1@esprit.tn")
                    .url("https://www.linkedin.com/in/**********/");
            return contact;
        }
    }
}
