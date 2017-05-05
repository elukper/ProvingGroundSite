package hr.altima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class XmlParserApplication {

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(XmlParserApplication.class, args);

		final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.getEnvironment().setActiveProfiles("dev");
		applicationContext.refresh();


	}
}
