package com.scheduler.bookservice.service.externalApi;

import java.util.Optional;

public interface AuthorizationExternalApi {
    Optional<String> getUserId(String login);
}
