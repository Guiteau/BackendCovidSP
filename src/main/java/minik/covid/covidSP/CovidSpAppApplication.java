package minik.covid.covidSP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CovidSpAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidSpAppApplication.class, args);
	}

}
