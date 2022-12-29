package tt.authorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tt.authorization.exception.IncorrectPasswordException;
import tt.authorization.model.User;
import tt.authorization.repository.UserRepository;
import tt.authorization.service.AuthService;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User authorize(String encodeEmailPassword) {
        final String decodeEmailPassword = new String(Base64.getDecoder().decode(encodeEmailPassword));
        final String[] emailPassword = decodeEmailPassword.split(":");
        final String email = emailPassword[0];
        final String password = emailPassword[1];

        final User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s not found", email)));

        if (passwordEncoder.encode(password).equals(user.getPassword())) {
            return user;
        } else {
            throw new IncorrectPasswordException("Incorrect password");
        }
    }
}
