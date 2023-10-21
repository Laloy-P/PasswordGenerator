package papalima.passwordgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import papalima.passwordgenerator.services.PasswordGeneratorService;

@SpringBootApplication
public class PasswordGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordGeneratorApplication.class, args);
	}

}
