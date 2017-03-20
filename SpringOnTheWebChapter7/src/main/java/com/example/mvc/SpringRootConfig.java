package com.example.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.example.spittles","com.example.spitters"})
public class SpringRootConfig {

}
