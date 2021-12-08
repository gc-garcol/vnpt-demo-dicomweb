package gc.garcol.demodicomweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author garcol
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }

}
