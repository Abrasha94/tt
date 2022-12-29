package tt.hashtranslator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tt.hashtranslator.model.Hash;

public interface HashRepository extends MongoRepository<Hash, String> {
}
