package tt.authorization.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class UserCreateDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Min(5)
    private String password;
}
