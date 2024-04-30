package academy.mischok.mvcDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {



    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
