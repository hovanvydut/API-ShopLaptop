package hovanvydut.shoplaptop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class ShopLaptopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopLaptopApplication.class, args);
    }

}
