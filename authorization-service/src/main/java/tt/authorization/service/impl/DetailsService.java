package tt.authorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tt.authorization.model.User;
import tt.authorization.repository.UserRepository;

import java.util.Optional;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class DetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> userByEmail = userRepository.findUserByEmail(username);
        return userByEmail.orElseThrow(() ->
                new UsernameNotFoundException(format("User with email: %s not found", username)));
    }
}
