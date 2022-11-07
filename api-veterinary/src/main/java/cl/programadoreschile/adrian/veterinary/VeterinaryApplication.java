package cl.programadoreschile.adrian.veterinary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cl.*"})
public class VeterinaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeterinaryApplication.class, args);
    }

}
