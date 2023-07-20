package com.dansmultipro.test.jobwebservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "defaultRestTemplateBean")
    public RestTemplate defaultRestTemplate() {
        SimpleClientHttpRequestFactory c = new SimpleClientHttpRequestFactory();
        c.setConnectTimeout(3000);
        c.setReadTimeout(3000);
        return new RestTemplate(c);
    }

}
