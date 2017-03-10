package com.example.soundsystemconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//In order to enable Component Scan, a class like this mus be created and marked with @Configuration annotation
@Configuration
//it also must be marked with @ComponenetScan, which specifies which package to scan
@ComponentScan("com.example.soundsystem")
public class JukeboxConfig {
	

}
