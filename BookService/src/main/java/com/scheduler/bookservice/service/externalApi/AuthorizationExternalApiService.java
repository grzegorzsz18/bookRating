package com.scheduler.bookservice.service.externalApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AuthorizationExternalApiService implements AuthorizationExternalApi {

    private final RestTemplate restTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(AuthorizationExternalApiService.class);

    public AuthorizationExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<String> getUserId(String login) {
        ResponseEntity<String> result = restTemplate
                .exchange("http://authorization-service/user/id/{login}",
                        HttpMethod.GET, null, String.class, login);
        if(!result.getStatusCode().is2xxSuccessful()){
            LOGGER.error("connection error {}", restTemplate);
            return Optional.empty();
        }
        return Optional.ofNullable(result.getBody());
    }
}
