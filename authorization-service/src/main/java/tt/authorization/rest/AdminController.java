package tt.authorization.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import tt.authorization.dto.request.UserCreateDto;
import tt.authorization.dto.response.ResponseUserDto;
import tt.authorization.model.User;
import tt.authorization.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/v1/authorization/admin/")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userServiceImpl;

    @PostMapping("users")
    public ResponseEntity<ResponseUserDto> createUser(@RequestBody UserCreateDto dto) {

        try {
            final User user = userServiceImpl.createUser(dto);
            return new ResponseEntity<>(ResponseUserDto.fromUser(user), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User not created", e);
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long userId) {

        try {
            userServiceImpl.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error on server", e);
        }
    }
}
