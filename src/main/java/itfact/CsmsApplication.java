package itfact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@EnableScheduling
@SpringBootApplication
public class CsmsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CsmsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CsmsApplication.class);
	}

}
