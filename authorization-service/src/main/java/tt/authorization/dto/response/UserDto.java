package tt.authorization.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tt.authorization.model.User;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotEmpty
    private String email;

    public static UserDto fromUser(User user) {
        return new UserDto(user.getEmail());
    }
}
