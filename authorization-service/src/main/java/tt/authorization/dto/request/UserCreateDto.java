package tt.authorization.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Min(5)
    private String password;
}
