package com.laynefongx.hodgepodge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@ImportResource(locations = {"classpath:spring-config.xml"})
@SpringBootApplication
public class BootstrapApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BootstrapApplication.class, args);
    }

}
