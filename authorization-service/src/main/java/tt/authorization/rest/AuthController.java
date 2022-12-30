package tt.authorization.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tt.authorization.exception.IncorrectPasswordException;
import tt.authorization.service.AuthService;

@Slf4j
@RestController
@RequestMapping("/api/v1/authorization/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("auth")
    public ResponseEntity<HttpStatus> authorize(@RequestBody String encodeEmailPassword) {

        try {
            authService.authorize(encodeEmailPassword);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (UsernameNotFoundException | IncorrectPasswordException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
