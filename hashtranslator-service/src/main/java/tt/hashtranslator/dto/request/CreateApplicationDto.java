package tt.hashtranslator.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateApplicationDto {

    @NotEmpty
    private List<String> hashes;
}
