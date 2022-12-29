package tt.authorization.service;

import tt.authorization.model.User;

public interface AuthService {

    User authorize(String encodeEmailPassword);
}
