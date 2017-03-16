package com.example.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Secondary @Configuration class, performs @ComponenetScan of beans that are not web-related
@Configuration
@ComponentScan({"com.example.spittles","com.example.spitters"})
public class SpringRootConfig {

}
