package com.akshit.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class YakYakService {
    @Value("${location-service-host}")
    String locationServiceHost;

    @Autowired RestTemplate restTemplate;

    @Scheduled(fixedRate = 1000)
    public void preach() {
        String city = "surat";
        String state = restTemplate.getForObject(
                String.format("http://%s:8080/state?city=%s", locationServiceHost, city),
                String.class);
        log.info("{} is part of {}",city, state);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
