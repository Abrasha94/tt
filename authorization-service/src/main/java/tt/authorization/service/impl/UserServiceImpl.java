package tt.authorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tt.authorization.dto.request.UserCreateDto;
import tt.authorization.model.User;
import tt.authorization.repository.UserRepository;
import tt.authorization.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreateDto dto) {
        final User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }
}
