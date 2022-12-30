package tt.authorization.service;

import tt.authorization.dto.request.UserCreateDto;
import tt.authorization.model.User;

public interface UserService {

    User createUser(UserCreateDto dto);

    void deleteUser(Long userId);
}
