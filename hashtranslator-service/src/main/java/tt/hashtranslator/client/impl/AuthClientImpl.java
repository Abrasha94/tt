package tt.hashtranslator.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tt.hashtranslator.client.AuthClient;

@Service
@RequiredArgsConstructor
public class AuthClientImpl implements AuthClient {

    public static final String AUTH_URL = "http://authorization-service:8080/api/v1/authorization/auth";

    private final RestTemplate authRestTemplate;

    @Override
    public HttpStatus authorize(String authHeader) {
        final String encodeEmailPassword = authHeader.substring(6);

        final ResponseEntity<HttpStatus> response =
                authRestTemplate.postForEntity(AUTH_URL, encodeEmailPassword, HttpStatus.class);

        return response.getStatusCode();
    }
}
