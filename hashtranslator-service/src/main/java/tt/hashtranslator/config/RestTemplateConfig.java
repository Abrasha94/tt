package tt.hashtranslator.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean("authRestTemplate")
    public RestTemplate getAuthRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri("/api/v1/authorization/").build();
    }

    @Bean("hashRestTemplate")
    public RestTemplate getHashRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.rootUri("/api/hash/").build();
    }
}
