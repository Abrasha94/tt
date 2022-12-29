package tt.hashtranslator.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Data
public class Application {

    @Id
    private String id;

    @Field("hashes")
    private List<Hash> hashes;
}
