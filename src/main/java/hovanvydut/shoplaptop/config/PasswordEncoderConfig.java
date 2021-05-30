package hovanvydut.shoplaptop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author hovanvydut
 * Created on 5/28/21
 */

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public static PasswordEncoder myBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

}
