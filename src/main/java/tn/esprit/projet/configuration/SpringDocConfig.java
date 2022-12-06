package tn.esprit.projet.configuration;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tn.esprit.projet.entities.Contrat;
import tn.esprit.projet.entities.Reclamation;
import tn.esprit.projet.services.IContratService;
import tn.esprit.projet.services.IReclamationService;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(infoAPI());

    }
    public Info infoAPI() {
        return new Info().title("SpringDoc Kaddem").description("Team Kaddem").contact(contactAPI());

    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Equipe ASI II")
                .email("assistantservice@esprit.tn")
                .url("https://www.linkedin.com/in/bestegroupe/");
        return contact;
    }

    @Bean
    public GroupedOpenApi All() {
        return GroupedOpenApi.builder()

                .group("All API")
                .pathsToMatch("/**")
                .build();

    }

    @Bean
    public GroupedOpenApi ContratPublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Contrat Management API")
                .pathsToMatch("/Contrat/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi ReclamationPublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Reclamation Management API")
                .pathsToMatch("/Reclamation/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi DepartementPublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Departement Management API")
                .pathsToMatch("/Departement/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi DetailEquipePublicApi() {
        return GroupedOpenApi.builder()

                .group("Only DetailEquipe Management API")
                .pathsToMatch("/DetailEquipe/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi EquipePublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Equipe Management API")
                .pathsToMatch("/Equipe/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi EtudiantPublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Etudiant Management API")
                .pathsToMatch("/Etudiant/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi FormationPublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Formation Management API")
                .pathsToMatch("/Formation/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi UniversitePublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Universite Management API")
                .pathsToMatch("/Universite/**")
                .pathsToExclude("**")
                .build();

    }

    @Bean
    public GroupedOpenApi CoursPublicApi() {
        return GroupedOpenApi.builder()

                .group("Only Cours Management API")
                .pathsToMatch("/Cours/**")
                .pathsToExclude("**")
                .build();

    }

}