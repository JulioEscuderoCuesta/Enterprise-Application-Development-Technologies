package es.unican.empresariales.julio.polaflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("es.unican.empresariales.julio.polaflix.repositories")
@ComponentScan("es.unican.empresariales.julio.polaflix")
@EntityScan("es.unican.empresariales.julio.polaflix.entities")
public class PolaflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolaflixApplication.class, args);
	}

}
