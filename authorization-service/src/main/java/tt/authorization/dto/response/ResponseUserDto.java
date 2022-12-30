package tt.authorization.dto.response;

import lombok.Data;
import tt.authorization.model.User;

import javax.validation.constraints.NotEmpty;

@Data
public class ResponseUserDto {

    @NotEmpty
    private String email;

    public static ResponseUserDto fromUser(User user) {
        final ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setEmail(user.getEmail());
        return responseUserDto;
    }
}
