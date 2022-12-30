package tt.authorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tt.authorization.dto.request.UserCreateDto;
import tt.authorization.exception.UserExistException;
import tt.authorization.model.User;
import tt.authorization.repository.UserRepository;
import tt.authorization.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreateDto dto) {

        final Optional<User> optionalUser = userRepository.findUserByEmail(dto.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserExistException("User already exist");
        } else {
            final User user = new User();
            user.setEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));

            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }
}
