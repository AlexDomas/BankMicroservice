package tech.idftechnology.domas.bankmicroservice.bankmicroservice.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "twelvedata.api")
public class ApiProperties {

    private String baseApiUrl;

    private String apiKey;

}
