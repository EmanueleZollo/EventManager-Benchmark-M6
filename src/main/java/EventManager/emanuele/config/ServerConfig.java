package EventManager.emanuele.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean
    public Cloudinary cloudinaryUploader(@Value("${cloudinary.name}") String name,
                                         @Value("${cloudinary.apikey}") String apikey,
                                         @Value("${cloudinary.secret}") String secret) {

    }
}