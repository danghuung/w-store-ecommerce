package app.service.wstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing()
public class WstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WstoreApplication.class, args);
	}

}
