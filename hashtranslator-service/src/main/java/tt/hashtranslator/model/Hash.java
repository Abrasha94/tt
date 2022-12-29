package tt.hashtranslator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Hash {

    @Id
    private String id;
    private String hash;
    private String decodeResult;

    public Hash(String hash) {
        this.hash = hash;
    }
}
