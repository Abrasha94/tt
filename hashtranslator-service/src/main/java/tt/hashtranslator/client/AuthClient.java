package tt.hashtranslator.client;

import org.springframework.http.HttpStatus;

public interface AuthClient {

    HttpStatus authorize(String authHeader);
}
