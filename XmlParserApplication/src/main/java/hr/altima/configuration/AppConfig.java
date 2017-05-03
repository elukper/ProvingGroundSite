package hr.altima.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"hr.altima","hr.altima.model.service","hr.altima.model.dao","hr.altima.utils"})
public class AppConfig {

}
